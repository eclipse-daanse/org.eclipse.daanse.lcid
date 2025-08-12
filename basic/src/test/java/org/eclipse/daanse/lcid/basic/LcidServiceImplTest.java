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

import org.eclipse.daanse.lcid.api.LcidService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LcidServiceImplTest {

    private LcidService service;

    @BeforeEach
    void setUp() {
        service = new BasicLcidService();
    }

    @Test
    void testLcidToLocaleWithValidEnglishUS() {
        Optional<Locale> result = service.lcidToLocale(Optional.of(0x0409));

        assertTrue(result.isPresent());
        Locale locale = result.get();
        assertEquals("en", locale.getLanguage());
        assertEquals("US", locale.getCountry());
    }

    @Test
    void testLcidToLocaleWithValidGermanDE() {
        Optional<Locale> result = service.lcidToLocale(Optional.of(0x0407));

        assertTrue(result.isPresent());
        Locale locale = result.get();
        assertEquals("de", locale.getLanguage());
        assertEquals("DE", locale.getCountry());
    }

    @Test
    void testLcidToLocaleWithValidFrenchFR() {
        Optional<Locale> result = service.lcidToLocale(Optional.of(0x040C));

        assertTrue(result.isPresent());
        Locale locale = result.get();
        assertEquals("fr", locale.getLanguage());
        assertEquals("FR", locale.getCountry());
    }

    @Test
    void testLcidToLocaleWithPrimaryLanguageCode() {
        // Test primary language code (0x0009 = English)
        Optional<Locale> result = service.lcidToLocale(Optional.of(0x0009));

        assertTrue(result.isPresent());
        Locale locale = result.get();
        assertEquals("en", locale.getLanguage());
    }

    @Test
    void testLcidToLocaleWithChineseVariants() {
        // Test Chinese Traditional (Taiwan)
        Optional<Locale> zhTw = service.lcidToLocale(Optional.of(0x0404));
        assertTrue(zhTw.isPresent());
        assertEquals(Locale.forLanguageTag("zh-TW"), zhTw.get());

        // Test Chinese Simplified (China)
        Optional<Locale> zhCn = service.lcidToLocale(Optional.of(0x0804));
        assertTrue(zhCn.isPresent());
        assertEquals(Locale.forLanguageTag("zh-CN"), zhCn.get());
    }

    @Test
    void testLcidToLocaleWithArabicVariants() {
        // Test Arabic (Saudi Arabia)
        Optional<Locale> arSa = service.lcidToLocale(Optional.of(0x0401));
        assertTrue(arSa.isPresent());
        assertEquals(Locale.forLanguageTag("ar-SA"), arSa.get());

        // Test Arabic (Egypt)
        Optional<Locale> arEg = service.lcidToLocale(Optional.of(0x0C01));
        assertTrue(arEg.isPresent());
        assertEquals(Locale.forLanguageTag("ar-EG"), arEg.get());
    }

    @Test
    void testLcidToLocaleWithEnglishVariants() {
        // Test English (United Kingdom)
        Optional<Locale> enGb = service.lcidToLocale(Optional.of(0x0809));
        assertTrue(enGb.isPresent());
        assertEquals(Locale.forLanguageTag("en-GB"), enGb.get());

        // Test English (Australia)
        Optional<Locale> enAu = service.lcidToLocale(Optional.of(0x0C09));
        assertTrue(enAu.isPresent());
        assertEquals(Locale.forLanguageTag("en-AU"), enAu.get());

        // Test English (Canada)
        Optional<Locale> enCa = service.lcidToLocale(Optional.of(0x1009));
        assertTrue(enCa.isPresent());
        assertEquals(Locale.forLanguageTag("en-CA"), enCa.get());
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
    void testLcidToLocaleWithLcidHavingNullMsId() {
        // Test LCID that maps to LanguageID with null msId (0x007B)
        Optional<Locale> result = service.lcidToLocale(Optional.of(0x007B));

        assertTrue(result.isEmpty());
    }

    @Test
    void testLcidToLocaleWithZeroLcid() {
        Optional<Locale> result = service.lcidToLocale(Optional.of(0));

        assertTrue(result.isEmpty());
    }

    @Test
    void testLcidToLocaleWithNegativeLcid() {
        Optional<Locale> result = service.lcidToLocale(Optional.of(-1));

        assertTrue(result.isEmpty());
    }

    @Test
    void testLcidToLocaleWithMaxIntValue() {
        Optional<Locale> result = service.lcidToLocale(Optional.of(Integer.MAX_VALUE));

        assertTrue(result.isEmpty());
    }

    @Test
    void testParseLocaleMethod() {
        // Test the private parseLocale method indirectly through known working LCIDs

        // Single language code
        Optional<Locale> enResult = service.lcidToLocale(Optional.of(0x0009)); // "en"
        assertTrue(enResult.isPresent());
        assertEquals("en", enResult.get().getLanguage());
        assertEquals("", enResult.get().getCountry());

        // Language with country
        Optional<Locale> enUsResult = service.lcidToLocale(Optional.of(0x0409)); // "en_us"
        assertTrue(enUsResult.isPresent());
        assertEquals("en", enUsResult.get().getLanguage());
        assertEquals("US", enUsResult.get().getCountry());
    }

    @Test
    void testServiceImplementsInterface() {
        assertTrue(service instanceof LcidService);
    }

    @Test
    void testMultipleCalls() {
        // Test that service can be called multiple times with same result
        Optional<Locale> result1 = service.lcidToLocale(Optional.of(0x0409));
        Optional<Locale> result2 = service.lcidToLocale(Optional.of(0x0409));

        assertTrue(result1.isPresent());
        assertTrue(result2.isPresent());
        assertEquals(result1.get(), result2.get());
    }

    @Test
    void testConversionAccuracy() {
        // Test a variety of known LCIDs for accuracy
        testSingleConversion(0x0404, "zh", "TW"); // Chinese (Traditional, Taiwan)
        testSingleConversion(0x0405, "cs", "CZ"); // Czech (Czech Republic)
        testSingleConversion(0x0406, "da", "DK"); // Danish (Denmark)
        testSingleConversion(0x0408, "el", "GR"); // Greek (Greece)
        testSingleConversion(0x040A, "es", "ES"); // Spanish (Spain, Traditional Sort)
        testSingleConversion(0x040B, "fi", "FI"); // Finnish (Finland)
        testSingleConversion(0x040E, "hu", "HU"); // Hungarian (Hungary)
        testSingleConversion(0x0410, "it", "IT"); // Italian (Italy)
        testSingleConversion(0x0411, "ja", "JP"); // Japanese (Japan)
        testSingleConversion(0x0412, "ko", "KR"); // Korean (Korea)
        testSingleConversion(0x0413, "nl", "NL"); // Dutch (Netherlands)
        testSingleConversion(0x0414, "nb", "NO"); // Norwegian (Bokmål, Norway)
        testSingleConversion(0x0415, "pl", "PL"); // Polish (Poland)
        testSingleConversion(0x0416, "pt", "BR"); // Portuguese (Brazil)
        testSingleConversion(0x0419, "ru", "RU"); // Russian (Russia)
        testSingleConversion(0x041D, "sv", "SE"); // Swedish (Sweden)
    }

    private void testSingleConversion(int lcid, String expectedLanguage, String expectedCountry) {
        Optional<Locale> result = service.lcidToLocale(Optional.of(lcid));
        assertTrue(result.isPresent(), "LCID 0x" + Integer.toHexString(lcid) + " should convert to a locale");

        Locale locale = result.get();
        assertEquals(expectedLanguage, locale.getLanguage(),
                "LCID 0x" + Integer.toHexString(lcid) + " should have language " + expectedLanguage);
        assertEquals(expectedCountry, locale.getCountry(),
                "LCID 0x" + Integer.toHexString(lcid) + " should have country " + expectedCountry);
    }

}
