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
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default implementation of the {@link LcidService} interface.
 * <p>
 * This OSGi component provides LCID to Locale conversion functionality using the built-in language
 * identifier mappings.
 * </p>
 */
@Component(service = LcidService.class, scope = ServiceScope.SINGLETON)
public class BasicLcidService implements LcidService {

    private static final Logger logger = LoggerFactory.getLogger(BasicLcidService.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Locale> lcidToLocale(Optional<Integer> localeIdentifier) {
        logger.debug("Converting LCID to Locale: {}", localeIdentifier);

        if (localeIdentifier != null && localeIdentifier.isPresent()) {
            int lcidValue = localeIdentifier.get();
            LanguageID languageID = LanguageID.lookupByLcid((short) lcidValue);

            if (languageID != null) {
                String msId = languageID.getMsId();
                if (msId != null) {
                    try {
                        Locale locale = parseLocale(msId);
                        logger.debug("Successfully converted LCID {} to Locale {}", lcidValue, locale);
                        return Optional.of(locale);
                    } catch (RuntimeException e) {
                        logger.warn("Failed to parse locale string '{}' for LCID {}: {}", msId, lcidValue,
                                e.getMessage());
                    }
                } else {
                    logger.debug("No MS ID available for LCID {}", lcidValue);
                }
            } else {
                logger.debug("No LanguageID found for LCID {}", lcidValue);
            }
        } else {
            logger.debug("LCID value is null or empty");
        }

        return Optional.empty();
    }

    /**
     * Parses a locale string.
     *
     * <p>
     * The inverse operation of {@link java.util.Locale#toString()}.
     *
     * @param localeString Locale string, e.g. "en" or "en_US"
     * @return Java locale object
     */
    private Locale parseLocale(String localeString) {
        String[] strings = localeString.split("_");
        switch (strings.length) {
        case 1:
            return Locale.of(strings[0]);
        case 2:
            return Locale.of(strings[0], strings[1]);
        case 3:
            return Locale.of(strings[0], strings[1], strings[2]);
        default:
            throw new RuntimeException("bad locale string '" + localeString + "'");
        }
    }

}
