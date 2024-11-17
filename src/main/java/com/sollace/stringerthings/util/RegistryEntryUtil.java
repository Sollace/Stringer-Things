package com.sollace.stringerthings.util;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.jetbrains.annotations.Nullable;

import com.mojang.datafixers.util.Either;

import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryOwner;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

@SuppressWarnings({"unchecked", "rawtypes"})
public final class RegistryEntryUtil {
    // Mojank
    private static final Function<RegistryKey<?>, RegistryEntry<?>> ENTRIES = Util.memoize(ref -> {
        return (RegistryEntry)new IndirectReferenceEntry(ref.getRegistryRef(), ref);
    });

    public static <T> RegistryEntry<T> dynamicEntryOf(RegistryKey<T> key) {
        return (RegistryEntry<T>)ENTRIES.apply(key);
    }

    private static final class IndirectReferenceEntry<T> implements RegistryEntry<T> {

        @Nullable
        T value;
        private final RegistryKey<T> key;
        @Nullable
        private Registry<T> owner;

        public IndirectReferenceEntry(RegistryKey<Registry<T>> registry, RegistryKey<T> value) {
            this.key = value;

            DynamicRegistrySetupCallback.EVENT.register(registries -> {
                registries.registerEntryAdded(registry, (raw, id, object) -> {
                    if (matchesId(id)) {
                        this.value = object;
                        this.owner = registries.getOptional(registry).orElse(null);
                    }
                });
            });
        }

        @Override
        public T value() {
            return value;
        }

        @Override
        public boolean hasKeyAndValue() {
            return value != null;
        }

        @Override
        public boolean matchesId(Identifier id) {
            return key.getValue().equals(id);
        }

        @Override
        public boolean matchesKey(RegistryKey<T> key) {
            return this.key.equals(key);
        }

        @Override
        public boolean matches(Predicate<RegistryKey<T>> predicate) {
            return predicate.test(key);
        }

        @Override
        public boolean isIn(TagKey<T> tag) {
            return owner != null && value != null && owner.getEntry(value).isIn(tag);
        }

        @Override
        public boolean matches(RegistryEntry<T> entry) {
            return entry == this || entry.value() == value;
        }

        @Override
        public Stream<TagKey<T>> streamTags() {
            return owner != null && value != null ? owner.getEntry(value).streamTags() : Stream.empty();
        }

        @Override
        public Either<RegistryKey<T>, T> getKeyOrValue() {
            return value == null ? Either.left(key) : Either.right(value);
        }

        @Override
        public Optional<RegistryKey<T>> getKey() {
            return Optional.of(key);
        }

        @Override
        public Type getType() {
            return Type.REFERENCE;
        }

        @Override
        public boolean ownerEquals(RegistryEntryOwner<T> owner) {
            return this.owner != null && (this.owner == owner || this.owner.ownerEquals(owner));
        }
    }
}
