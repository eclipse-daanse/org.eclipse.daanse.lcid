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
package org.eclipse.daanse.lcid.cfg;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = Config.L10N_OCD_LCID_NAME, description = Config.L10N_OCD_LCID_DESCRIPTION, localization = Config.OCD_LOCALIZATION)
public interface Config {

    String OCD_LOCALIZATION = "OSGI-INF/l10n/org.eclipse.daanse.lcid.cfg";
    String L10N_PREFIX = "%";

    String L10N_POSTFIX_DESCRIPTION = ".description";
    String L10N_POSTFIX_NAME = ".name";

    String L10N_OCD_LCID_NAME = L10N_PREFIX + "ocd" + ".lcid" + L10N_POSTFIX_NAME;
    String L10N_OCD_LCID_DESCRIPTION = L10N_PREFIX + "ocd" + ".lcid" + L10N_POSTFIX_DESCRIPTION;

    String L10N_SERVICENAME_NAME = L10N_PREFIX + Constants.LCID_PROPERTY_SERVICENAME + L10N_POSTFIX_NAME;
    String L10N_SERVICENAME_DESCRIPTION = L10N_PREFIX + Constants.LCID_PROPERTY_SERVICENAME
            + L10N_POSTFIX_DESCRIPTION;

    String L10N_LOCALELANGUAGE_NAME = L10N_PREFIX + Constants.LCID_PROPERTY_LOCALELANGUAGE + L10N_POSTFIX_NAME;
    String L10N_LOCALELANGUAGE_DESCRIPTION = L10N_PREFIX + Constants.LCID_PROPERTY_LOCALELANGUAGE
            + L10N_POSTFIX_DESCRIPTION;

    String L10N_LOCALECOUNTRY_NAME = L10N_PREFIX + Constants.LCID_PROPERTY_LOCALECOUNTRY + L10N_POSTFIX_NAME;
    String L10N_LOCALECOUNTRY_DESCRIPTION = L10N_PREFIX + Constants.LCID_PROPERTY_LOCALECOUNTRY
            + L10N_POSTFIX_DESCRIPTION;

    String L10N_LOCALEVARIANT_NAME = L10N_PREFIX + Constants.LCID_PROPERTY_LOCALEVARIANT + L10N_POSTFIX_NAME;
    String L10N_LOCALEVARIANT_DESCRIPTION = L10N_PREFIX + Constants.LCID_PROPERTY_LOCALEVARIANT
            + L10N_POSTFIX_DESCRIPTION;

    // Default value constants
    String DEFAULT_SERVICE_NAME = "";
    String DEFAULT_LOCALE_LANGUAGE = "en";
    String DEFAULT_LOCALE_COUNTRY = "US";
    String DEFAULT_LOCALE_VARIANT = "";

    @AttributeDefinition(name = L10N_SERVICENAME_NAME, description = L10N_SERVICENAME_DESCRIPTION, defaultValue = DEFAULT_SERVICE_NAME)
    default String serviceName() {
        return DEFAULT_SERVICE_NAME;
    }

    @AttributeDefinition(name = L10N_LOCALELANGUAGE_NAME, description = L10N_LOCALELANGUAGE_DESCRIPTION, defaultValue = DEFAULT_LOCALE_LANGUAGE)
    default String localeLanguage() {
        return DEFAULT_LOCALE_LANGUAGE;
    }

    @AttributeDefinition(name = L10N_LOCALECOUNTRY_NAME, description = L10N_LOCALECOUNTRY_DESCRIPTION, defaultValue = DEFAULT_LOCALE_COUNTRY)
    default String localeCountry() {
    	return DEFAULT_LOCALE_COUNTRY;
    }

    @AttributeDefinition(name = L10N_LOCALEVARIANT_NAME, description = L10N_LOCALEVARIANT_DESCRIPTION, defaultValue = DEFAULT_LOCALE_VARIANT)
    default String localeVariant() {
        return DEFAULT_LOCALE_VARIANT;
    }

}
