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
package org.eclipse.daanse.lcid.cfg.impl;

import org.eclipse.daanse.lcid.cfg.api.Constants;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * Configuration interface for the LCID service using OSGi Metatype annotations.
 * <p>
 * This annotation interface defines the configuration properties available for the LCID service. It
 * uses OSGi Metatype Service annotations to provide metadata for configuration management and
 * administrative tools.
 * </p>
 * <p>
 * The configuration allows specifying locale components (language, country, variant) that will be
 * used to create a {@link java.util.Locale} instance for LCID conversion.
 * </p>
 *
 * @since 0.0.1
 * @see java.util.Locale
 * @see org.osgi.service.metatype.annotations.ObjectClassDefinition
 */
@ObjectClassDefinition(name = Config.L10N_OCD_LCID_NAME, description = Config.L10N_OCD_LCID_DESCRIPTION, localization = Config.OCD_LOCALIZATION)
public @interface Config {

    /** Path to the localization resource bundle for OSGi Metatype. */
    String OCD_LOCALIZATION = "OSGI-INF/l10n/org.eclipse.daanse.lcid.cfg";

    /** Prefix used for localized property keys. */
    String L10N_PREFIX = "%";

    /** Suffix used for localized description property keys. */
    String L10N_POSTFIX_DESCRIPTION = ".description";

    /** Suffix used for localized name property keys. */
    String L10N_POSTFIX_NAME = ".name";

    /** Localized name key for the LCID service configuration. */
    String L10N_OCD_LCID_NAME = L10N_PREFIX + "ocd" + ".lcid" + L10N_POSTFIX_NAME;

    /** Localized description key for the LCID service configuration. */
    String L10N_OCD_LCID_DESCRIPTION = L10N_PREFIX + "ocd" + ".lcid" + L10N_POSTFIX_DESCRIPTION;

    /** Localized name key for the locale language property. */
    String L10N_LOCALELANGUAGE_NAME = L10N_PREFIX + Constants.LCID_PROPERTY_LOCALELANGUAGE + L10N_POSTFIX_NAME;

    /** Localized description key for the locale language property. */
    String L10N_LOCALELANGUAGE_DESCRIPTION = L10N_PREFIX + Constants.LCID_PROPERTY_LOCALELANGUAGE
            + L10N_POSTFIX_DESCRIPTION;

    /** Localized name key for the locale country property. */
    String L10N_LOCALECOUNTRY_NAME = L10N_PREFIX + Constants.LCID_PROPERTY_LOCALECOUNTRY + L10N_POSTFIX_NAME;

    /** Localized description key for the locale country property. */
    String L10N_LOCALECOUNTRY_DESCRIPTION = L10N_PREFIX + Constants.LCID_PROPERTY_LOCALECOUNTRY
            + L10N_POSTFIX_DESCRIPTION;

    /** Localized name key for the locale variant property. */
    String L10N_LOCALEVARIANT_NAME = L10N_PREFIX + Constants.LCID_PROPERTY_LOCALEVARIANT + L10N_POSTFIX_NAME;

    /** Localized description key for the locale variant property. */
    String L10N_LOCALEVARIANT_DESCRIPTION = L10N_PREFIX + Constants.LCID_PROPERTY_LOCALEVARIANT
            + L10N_POSTFIX_DESCRIPTION;

    /**
     * Gets the locale language code.
     * <p>
     * This method returns the ISO 639 language code for the locale. The language code is a required
     * field for locale creation.
     * </p>
     *
     * @return the ISO 639 language code (e.g., "en", "de", "fr")
     * @see java.util.Locale#getLanguage()
     */
    @AttributeDefinition(name = L10N_LOCALELANGUAGE_NAME, description = L10N_LOCALELANGUAGE_DESCRIPTION, required = true)
    String localeLanguage() default Constants.DEFAULT_LOCALE_LANGUAGE;

    /**
     * Gets the locale country code.
     * <p>
     * This method returns the ISO 3166 country code for the locale. The country code is optional and
     * can be empty for language-only locales.
     * </p>
     *
     * @return the ISO 3166 country code (e.g., "US", "DE", "FR")
     * @see java.util.Locale#getCountry()
     */
    @AttributeDefinition(name = L10N_LOCALECOUNTRY_NAME, description = L10N_LOCALECOUNTRY_DESCRIPTION, required = false)
    String localeCountry() default Constants.DEFAULT_LOCALE_COUNTRY;

    /**
     * Gets the locale variant.
     * <p>
     * This method returns the locale variant identifier. The variant is optional and typically used for
     * platform-specific or vendor-specific locale variations.
     * </p>
     *
     * @return the locale variant (e.g., "POSIX", "MAC", or empty string)
     * @see java.util.Locale#getVariant()
     */
    @AttributeDefinition(name = L10N_LOCALEVARIANT_NAME, description = L10N_LOCALEVARIANT_DESCRIPTION, required = false)
    String localeVariant() default Constants.DEFAULT_LOCALE_VARIANT;

}
