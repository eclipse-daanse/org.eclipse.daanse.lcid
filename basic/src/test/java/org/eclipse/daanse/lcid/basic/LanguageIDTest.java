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
package org.eclipse.daanse.lcid.basic;

import java.util.Locale;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LanguageIDTest {

    @Test
    void testGetLanguageID() {
        assertEquals((short) 0x0409, LanguageID.LANG_0409.getLanguageID());
        assertEquals((short) 0x0407, LanguageID.LANG_0407.getLanguageID());
        assertEquals((short) 0x0001, LanguageID.LANG_0001.getLanguageID());
    }

    @Test
    void testGetMsId() {
        assertEquals("en_us", LanguageID.LANG_0409.getMsId());
        assertEquals("de_de", LanguageID.LANG_0407.getMsId());
        assertEquals("ar", LanguageID.LANG_0001.getMsId());
        assertNull(LanguageID.LANG_007B.getMsId()); // Entry with null msId
    }

    @Test
    void testGetLanguageTag() {
        Optional<String> enUsTag = LanguageID.LANG_0409.getLanguageTag();
        assertTrue(enUsTag.isPresent());
        assertEquals("en-US", enUsTag.get());

        Optional<String> deDeTag = LanguageID.LANG_0407.getLanguageTag();
        assertTrue(deDeTag.isPresent());
        assertEquals("de-DE", deDeTag.get());

        Optional<String> nullTag = LanguageID.LANG_007B.getLanguageTag();
        assertTrue(nullTag.isEmpty()); // Entry with null language tag
    }

    @Test
    void testGetLocale() {
        Optional<Locale> enUsLocale = LanguageID.LANG_0409.getLocale();
        assertTrue(enUsLocale.isPresent());
        assertEquals(Locale.forLanguageTag("en-US"), enUsLocale.get());

        Optional<Locale> deDeLocale = LanguageID.LANG_0407.getLocale();
        assertTrue(deDeLocale.isPresent());
        assertEquals(Locale.forLanguageTag("de-DE"), deDeLocale.get());

        Optional<Locale> nullLocale = LanguageID.LANG_007B.getLocale();
        assertTrue(nullLocale.isEmpty()); // Entry with null language tag
    }

    @Test
    void testLookupByLanguageTag() {
        assertEquals(LanguageID.LANG_0409, LanguageID.lookupByLanguageTag("en-US"));
        assertEquals(LanguageID.LANG_0407, LanguageID.lookupByLanguageTag("de-DE"));
        assertEquals(LanguageID.LANG_040C, LanguageID.lookupByLanguageTag("fr-FR"));
        assertNull(LanguageID.lookupByLanguageTag("non-existent"));
        assertNull(LanguageID.lookupByLanguageTag(null));
    }

    @Test
    void testLookupByLcid() {
        assertEquals(LanguageID.LANG_0409, LanguageID.lookupByLcid((short) 0x0409));
        assertEquals(LanguageID.LANG_0407, LanguageID.lookupByLcid((short) 0x0407));
        assertEquals(LanguageID.LANG_040C, LanguageID.lookupByLcid((short) 0x040C));
        assertNull(LanguageID.lookupByLcid((short) 0x9999)); // Non-existent LCID
    }

    @Test
    void testPrimaryLanguageLookup() {
        // Test primary language codes (0x0001-0x0093)
        assertEquals(LanguageID.LANG_0009, LanguageID.lookupByLcid((short) 0x0009)); // English
        assertEquals(LanguageID.LANG_0007, LanguageID.lookupByLcid((short) 0x0007)); // German
        assertEquals(LanguageID.LANG_000C, LanguageID.lookupByLcid((short) 0x000C)); // French
    }

    @Test
    void testSpecificLocaleLookup() {
        // Test specific locale codes (0x0400+)
        assertEquals(LanguageID.LANG_0409, LanguageID.lookupByLcid((short) 0x0409)); // en-US
        assertEquals(LanguageID.LANG_0809, LanguageID.lookupByLcid((short) 0x0809)); // en-GB
        assertEquals(LanguageID.LANG_0C09, LanguageID.lookupByLcid((short) 0x0C09)); // en-AU
    }

    @Test
    void testChineseVariants() {
        assertEquals(LanguageID.LANG_0004, LanguageID.lookupByLcid((short) 0x0004)); // zh_hans
        assertEquals(LanguageID.LANG_0404, LanguageID.lookupByLcid((short) 0x0404)); // zh_tw
        assertEquals(LanguageID.LANG_0804, LanguageID.lookupByLcid((short) 0x0804)); // zh_cn
        assertEquals(LanguageID.LANG_0C04, LanguageID.lookupByLcid((short) 0x0C04)); // zh_hk
    }

    @Test
    void testArabicVariants() {
        assertEquals(LanguageID.LANG_0001, LanguageID.lookupByLcid((short) 0x0001)); // ar
        assertEquals(LanguageID.LANG_0401, LanguageID.lookupByLcid((short) 0x0401)); // ar_sa
        assertEquals(LanguageID.LANG_0801, LanguageID.lookupByLcid((short) 0x0801)); // ar_iq
        assertEquals(LanguageID.LANG_0C01, LanguageID.lookupByLcid((short) 0x0C01)); // ar_eg
    }

    @Test
    void testEntriesWithNullValues() {
        // Test entries that have null msId and languageTag
        LanguageID nullEntry = LanguageID.LANG_007B;
        assertNull(nullEntry.getMsId());
        assertTrue(nullEntry.getLanguageTag().isEmpty());
        assertTrue(nullEntry.getLocale().isEmpty());
        assertEquals((short) 0x007B, nullEntry.getLanguageID());
    }

    @Test
    void testMsIdToLanguageTagConsistency() {
        // Test that entries with non-null msId also have non-null language tags
        for (LanguageID langId : LanguageID.values()) {
            String msId = langId.getMsId();
            Optional<String> languageTag = langId.getLanguageTag();

            // If msId is not null, languageTag should also be present (with very few exceptions)
            if (msId != null && !msId.equals("fr-015")) { // Known exception
                assertTrue(languageTag.isPresent(),
                        "LanguageID " + langId + " has msId '" + msId + "' but no language tag");
            }
        }
    }

    @Test
    void testLocaleCreation() {
        // Test that valid language tags create proper Locale objects
        Optional<Locale> locale = LanguageID.LANG_0409.getLocale();
        assertTrue(locale.isPresent());
        assertEquals("en", locale.get().getLanguage());
        assertEquals("US", locale.get().getCountry());
    }

    @Test
    void testLookupRoundTrip() {
        // Test that lookup functions work in both directions for valid entries
        LanguageID original = LanguageID.LANG_0409;

        // LCID lookup round trip
        LanguageID fromLcid = LanguageID.lookupByLcid(original.getLanguageID());
        assertEquals(original, fromLcid);

        // Language tag lookup round trip (if available)
        if (original.getLanguageTag().isPresent()) {
            LanguageID fromTag = LanguageID.lookupByLanguageTag(original.getLanguageTag().get());
            assertEquals(original, fromTag);
        }
    }

    @Test
    void testAllEnumValues() {
        // Ensure all enum values have valid language IDs
        for (LanguageID langId : LanguageID.values()) {
            assertNotNull(langId.getLanguageID());
            assertNotNull(langId.getLanguageTag()); // Optional can't be null
        }
    }
}
