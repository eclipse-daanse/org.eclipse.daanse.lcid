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

/**
 * Represents a Windows Locale Identifier (LCID).
 * <p>
 * An LCID is a 32-bit value that uniquely defines a locale. It consists of a
 * language identifier (lower 16 bits) and a sort identifier (upper 16 bits).
 * This record provides convenient constructors for creating LCID values from
 * their components.
 * </p>
 *
 * @param lcid the complete LCID value as a 32-bit integer
 *
 * @see <a href=
 *      "https://docs.microsoft.com/en-us/openspecs/windows_protocols/ms-lcid/">MS-LCID:
 *      Windows Language Code Identifier (LCID) Reference</a>
 */
public record Lcid(int lcid) {

    /**
     * Creates an LCID from a language identifier and sort identifier.
     *
     * @param languageId the language identifier (16-bit value)
     * @param sortId     the sort identifier (8-bit value)
     */
    public Lcid(short languageId, byte sortId) {
        this((sortId << 16) | languageId);
    }

    /**
     * Creates an LCID with the default sort identifier.
     *
     * @param languageId the language identifier (16-bit value)
     */
    public Lcid(short languageId) {
        this(languageId, SortId.SORT_DEFAULT);
    }

    /**
     * Creates an LCID from a language identifier and sort identifier enum. The sort
     * identifier is validated against the language identifier.
     *
     * @param languageId the language identifier (16-bit value)
     * @param sortId     the sort identifier enum value
     * @throws IllegalArgumentException if the sort identifier is not valid for the
     *                                  language
     */
    public Lcid(short languageId, SortId sortId) {
        this(languageId, SortId.getValidatedValue(languageId, sortId));
    }

}
