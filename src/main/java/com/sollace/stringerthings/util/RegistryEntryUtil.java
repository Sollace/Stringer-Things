package com.sollace.stringerthings.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryOwner;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

@SuppressWarnings({"unchecked", "rawtypes"})
public final class RegistryEntryUtil {
    // Mojank
    private static final Function<RegistryKey<Registry<?>>, ReferenceMaker<?>> REFERENCE_MAKERS = Util.memoize(ReferenceMaker::new);

    public static <T> RegistryEntry<T> dynamicEntryOf(RegistryKey<T> key) {
        return ((ReferenceMaker<T>)REFERENCE_MAKERS.apply((RegistryKey)key.getRegistryRef())).get(key);
    }

    private static final class ReferenceMaker<T> implements RegistryEntryOwner<T> {
        private final Map<Identifier, Reference> references = new HashMap<>();

        private ReferenceMaker(RegistryKey<Registry<T>> key) {
            DynamicRegistrySetupCallback.EVENT.register(registries -> {
                registries.registerEntryAdded(key, (raw, id, value) -> {
                    Reference ref = references.getOrDefault(id, null);
                    if (ref != null) {
                        ref.setValue(value);
                    }
                });
            });
        }

        public RegistryEntry<T> get(RegistryKey<T> key) {
            return references.computeIfAbsent(key.getValue(), v -> new Reference(key));
        }

        private final class Reference extends RegistryEntry.Reference<T> {
            private Reference(RegistryKey<T> key) {
                super(Type.STAND_ALONE, ReferenceMaker.this, key, null);
            }

            @Override
            public void setValue(T value) {
                super.setValue(value);
            }
        }
    }
}
