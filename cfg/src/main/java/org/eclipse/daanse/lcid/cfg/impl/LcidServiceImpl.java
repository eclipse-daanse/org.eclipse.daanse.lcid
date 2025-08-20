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
package org.eclipse.daanse.lcid.cfg.impl;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.eclipse.daanse.lcid.api.LcidService;
import org.eclipse.daanse.lcid.cfg.api.Constants;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default implementation of the {@link LcidService} interface.
 * <p>
 * This OSGi component provides LCID to Locale conversion functionality by maintaining
 * a configured {@link Locale} instance that is returned for all LCID conversion requests.
 * The locale is configured through OSGi Configuration Admin using the {@link Config} interface.
 * </p>
 * <p>
 * This implementation uses a factory configuration pattern, allowing multiple instances
 * of the service to be created with different locale configurations. Each instance
 * is registered as a singleton-scoped OSGi service.
 * </p>
 *
 * <h1>Configuration</h1>
 * <p>
 * The service is configured through the following properties:
 * </p>
 * <ul>
 *   <li><strong>localeLanguage</strong>: ISO 639 language code (required, default: "en")</li>
 *   <li><strong>localeCountry</strong>: ISO 3166 country code (optional, default: "US")</li>
 *   <li><strong>localeVariant</strong>: Locale variant (optional, default: "")</li>
 * </ul>
 *
 * <h3>Usage Example</h3>
 * <pre>{@code
 * // Service will return the configured locale for any LCID input
 * Optional<Locale> locale = lcidService.lcidToLocale(Optional.of(1033));
 * // Returns the configured locale (e.g., Locale.of("en", "US", ""))
 * }</pre>
 *
 * @since 0.0.1
 * @see LcidService
 * @see Config
 * @see java.util.Locale
 */
@Designate(ocd = Config.class, factory = true)
@Component(service = LcidService.class, scope = ServiceScope.SINGLETON, configurationPid = Constants.LCID_PID)
public class LcidServiceImpl implements LcidService {

    private static final Logger logger = LoggerFactory.getLogger(LcidServiceImpl.class);

    /**
     * The configured locale instance wrapped in an Optional.
     * This field is initialized during component activation and cleared during deactivation.
     */
    private Optional<Locale> oLocale;

    /**
     * Activates the LCID service component with the provided configuration.
     * <p>
     * This method is called by the OSGi framework when the component is activated.
     * It creates a {@link Locale} instance from the configuration parameters and
     * initializes the service for use.
     * </p>
     *
     * @param config the OSGi configuration containing locale parameters
     * @throws IllegalArgumentException if the locale parameters are invalid
     * @see Config
     * @see Locale#of(String, String, String)
     */
    @Activate
    public void activate(Config config) {
        logger.info("Activating LCID service with configuration - Language: '{}', Country: '{}', Variant: '{}'",
                config.localeLanguage(), config.localeCountry(), config.localeVariant());

        try {
            Locale locale = Locale.of(config.localeLanguage(), config.localeCountry(), config.localeVariant());
            oLocale = Optional.of(locale);
            logger.info("LCID service activated successfully with locale: '{}'", locale);
        } catch (Exception e) {
            logger.error("Failed to create locale from configuration - Language: '{}', Country: '{}', Variant: '{}'",
                    config.localeLanguage(), config.localeCountry(), config.localeVariant(), e);
            throw e;
        }
    }

    /**
     * Deactivates the LCID service component.
     * <p>
     * This method is called by the OSGi framework when the component is being
     * deactivated. It clears the configured locale and performs cleanup.
     * </p>
     *
     * @param configMap the configuration map (unused in this implementation)
     */
    @Deactivate
    public void deactivate(Map<String, Object> configMap) {
        logger.info("Deactivating LCID service");
        oLocale = Optional.empty();
        logger.debug("LCID service deactivated successfully");
    }

    /**
     * Converts a Locale Identifier (LCID) to a Java Locale.
     * <p>
     * This implementation returns the configured locale regardless of the input LCID value.
     * The actual LCID parameter is currently ignored, as this service provides a
     * static locale configuration rather than dynamic LCID-to-Locale mapping.
     * </p>
     * <p>
     * <strong>Note:</strong> This is a simplified implementation. In a complete LCID service,
     * the input parameter would be used to look up the corresponding locale from
     * Microsoft's LCID tables or similar mapping mechanisms.
     * </p>
     *
     * @param localeIdentifier the optional LCID to convert (currently ignored)
     * @return the configured locale, or empty Optional if the service is not initialized
     * @see LcidService#lcidToLocale(Optional)
     * @see java.util.Locale
     */
    @Override
    public Optional<Locale> lcidToLocale(Optional<Integer> localeIdentifier) {
        logger.debug("Converting LCID '{}' to locale", localeIdentifier.orElse(null));

        if (oLocale.isEmpty()) {
            logger.warn("LCID service not properly initialized - no locale available");
            return Optional.empty();
        }

        logger.debug("Returning configured locale: '{}'", oLocale.get());
        return oLocale;
    }

}
