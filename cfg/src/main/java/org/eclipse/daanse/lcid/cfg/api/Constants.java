/*
* Copyright (c) 2024 Contributors to the Eclipse Foundation.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
*
* Contributors:
*   SmartCity Jena - initial
*   Stefan Bischof (bipolis.org) - initial
*/
package org.eclipse.daanse.lcid.cfg.api;

/**
 * Constants used throughout the LCID configuration module.
 * <p>
 * This class contains all configuration property names and identifiers used by the LCID service
 * configuration system.
 * </p>
 *
 * @since 0.0.1
 */
public class Constants {

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private Constants() {
    }

    /**
     * Configuration property name for the locale language component.
     * <p>
     * This property defines the ISO 639 language code (e.g., "en", "de", "fr"). Used in OSGi service
     * configuration to specify the language part of the locale.
     * </p>
     *
     * @see java.util.Locale#getLanguage()
     */
    public static final String LCID_PROPERTY_LOCALELANGUAGE = "localeLanguage";

    /**
     * Configuration property name for the locale country component.
     * <p>
     * This property defines the ISO 3166 country code (e.g., "US", "DE", "FR"). Used in OSGi service
     * configuration to specify the country part of the locale.
     * </p>
     *
     * @see java.util.Locale#getCountry()
     */
    public static final String LCID_PROPERTY_LOCALECOUNTRY = "localeCountry";

    /**
     * Configuration property name for the locale variant component.
     * <p>
     * This property defines the locale variant (e.g., "POSIX", "MAC"). Used in OSGi service
     * configuration to specify the variant part of the locale. Can be empty for most standard locales.
     * </p>
     *
     * @see java.util.Locale#getVariant()
     */
    public static final String LCID_PROPERTY_LOCALEVARIANT = "localeVariant";

    /**
     * The OSGi configuration PID (Persistent Identifier) for the LCID service.
     * <p>
     * This PID is used by the OSGi Configuration Admin service to identify and manage configuration
     * instances for the LCID service.
     * </p>
     */
    public static final String LCID_PID = "daanse.lcid.cfg.LcidService";

    /** Default locale language code (English). */
    public static final String DEFAULT_LOCALE_LANGUAGE = "en";

    /** Default locale country code (United States). */
    public static final String DEFAULT_LOCALE_COUNTRY = "US";

    /** Default locale variant (empty for standard locales). */
    public static final String DEFAULT_LOCALE_VARIANT = "";
}
