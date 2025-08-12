/*
 * Copyright (c) 2025 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   SmartCity Jena, Stefan Bischof - initial
 *
 */
package org.eclipse.daanse.lcid.api;

import java.util.Locale;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LcidServiceTest {

    private static final class TestLcidService implements LcidService {
        @Override
        public Optional<Locale> lcidToLocale(Optional<Integer> value) {
            if (value == null || value.isEmpty()) {
                return Optional.empty();
            }

            int lcidValue = value.get();
            if (lcidValue == 0x0409) { // English (United States)
                return Optional.of(Locale.forLanguageTag("en-US"));
            }
            if (lcidValue == 0x0407) { // German (Germany)
                return Optional.of(Locale.forLanguageTag("de-DE"));
            }

            return Optional.empty();
        }
    }

    private final LcidService service = new TestLcidService();

    @Test
    void testLcidToLocaleWithValidLcid() {
        Optional<Locale> result = service.lcidToLocale(Optional.of(0x0409));

        assertTrue(result.isPresent());
        assertEquals(Locale.forLanguageTag("en-US"), result.get());
    }

    @Test
    void testLcidToLocaleWithAnotherValidLcid() {
        Optional<Locale> result = service.lcidToLocale(Optional.of(0x0407));

        assertTrue(result.isPresent());
        assertEquals(Locale.forLanguageTag("de-DE"), result.get());
    }

    @Test
    void testLcidToLocaleWithInvalidLcid() {
        Optional<Locale> result = service.lcidToLocale(Optional.of(0x9999));

        assertTrue(result.isEmpty());
    }

    @Test
    void testLcidToLocaleWithNullValue() {
        Optional<Locale> result = service.lcidToLocale(null);

        assertTrue(result.isEmpty());
    }

    @Test
    void testLcidToLocaleWithEmptyOptional() {
        Optional<Locale> result = service.lcidToLocale(Optional.empty());

        assertTrue(result.isEmpty());
    }

    @Test
    void testMethodSignature() {
        assertDoesNotThrow(() -> {
            service.lcidToLocale(Optional.of(0x0409));
            service.lcidToLocale(Optional.empty());
            service.lcidToLocale(null);
        });
    }
}
