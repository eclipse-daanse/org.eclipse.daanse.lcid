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

/**
 * Service interface for converting Windows LCID values to Java Locale objects.
 * <p>
 * This service provides functionality to convert Microsoft Windows Locale Identifiers (LCID) to
 * their corresponding Java Locale representations.
 * </p>
 */
public interface LcidService {

    /**
     * Converts an LCID value to a Java Locale.
     *
     * @param value an Optional containing the LCID value to convert, may be null or empty
     * @return an Optional containing the corresponding Locale, or empty if conversion is not possible
     */
    Optional<Locale> lcidToLocale(Optional<Integer> value);

}
