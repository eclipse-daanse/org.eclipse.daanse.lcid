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

/**
 * Constants of this {@link Bundle}.
 */
public class Constants {

    private Constants() {
    }


    /**
     * Constant for Properties of the Service that could be configured using the
     * lcid service.
     *
     * {@link org.eclipse.daanse.lcid.api.ocd.Config#serviceName()}
     */
    public static final String LCID_PROPERTY_SERVICENAME = "serviceName";
    public static final String LCID_PROPERTY_LOCALELANGUAGE = "localeLanguage";
    public static final String LCID_PROPERTY_LOCALECOUNTRY = "localeCountry";
    public static final String LCID_PROPERTY_LOCALEVARIANT = "localeVariant";
    public static final String LCID_PID = "org.eclipse.daanse.lcid.csv.LcidServiceImpl";
}
