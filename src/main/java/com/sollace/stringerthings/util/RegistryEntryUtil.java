package com.sollace.stringerthings.util;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.jetbrains.annotations.Nullable;

import com.mojang.datafixers.util.Either;

import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Util;

@SuppressWarnings({"unchecked", "rawtypes"})
public final class RegistryEntryUtil {
    // Mojank
    private static final Function<ResourceKey<?>, Holder<?>> ENTRIES = Util.memoize(ref -> {
        return (Holder)new IndirectReferenceEntry(ref.registryKey(), ref);
    });

    public static <T> Holder<T> dynamicEntryOf(ResourceKey<T> key) {
        return (Holder<T>)ENTRIES.apply(key);
    }

    private static final class IndirectReferenceEntry<T> implements Holder<T> {

        @Nullable
        T value;
        private final ResourceKey<T> key;
        @Nullable
        private Registry<T> owner;

        public IndirectReferenceEntry(ResourceKey<Registry<T>> registry, ResourceKey<T> value) {
            this.key = value;

            DynamicRegistrySetupCallback.EVENT.register(registries -> {
                registries.registerEntryAdded(registry, (raw, id, object) -> {
                    if (is(id)) {
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
        public boolean isBound() {
            return value != null;
        }

        @Override
        public boolean is(Identifier id) {
            return key.identifier().equals(id);
        }

        @Override
        public boolean is(ResourceKey<T> key) {
            return this.key.equals(key);
        }

        @Override
        public boolean is(Predicate<ResourceKey<T>> predicate) {
            return predicate.test(key);
        }

        @Override
        public boolean is(TagKey<T> tag) {
            return owner != null && value != null && owner.wrapAsHolder(value).is(tag);
        }

        @Override
        public boolean is(Holder<T> entry) {
            return entry == this || entry.value() == value;
        }

        @Override
        public Stream<TagKey<T>> tags() {
            return owner != null && value != null ? owner.wrapAsHolder(value).tags() : Stream.empty();
        }

        @Override
        public Either<ResourceKey<T>, T> unwrap() {
            return value == null ? Either.left(key) : Either.right(value);
        }

        @Override
        public Optional<ResourceKey<T>> unwrapKey() {
            return Optional.of(key);
        }

        @Override
        public Kind kind() {
            return Kind.REFERENCE;
        }

        @Override
        public boolean canSerializeIn(HolderOwner<T> owner) {
            return this.owner != null && (this.owner == owner || this.owner.canSerializeIn(owner));
        }
    }
}
