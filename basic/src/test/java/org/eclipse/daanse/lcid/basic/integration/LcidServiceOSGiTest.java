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
package org.eclipse.daanse.lcid.basic.integration;

import java.util.Locale;
import java.util.Optional;

import org.assertj.core.api.ListAssert;
import org.eclipse.daanse.lcid.api.LcidService;
import org.junit.jupiter.api.Test;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.common.service.ServiceAware;

class LcidServiceOSGiTest {

    @Test
    void serviceExists(@InjectService ServiceAware<LcidService> saLcidService) {

        ListAssert.assertThatList(saLcidService.getServices()).hasSize(1).allSatisfy(lcidService -> {
            // Test with a known LCID
            lcidService.lcidToLocale(Optional.of(0x0409)).ifPresent(locale -> {
                assert locale.equals(Locale.forLanguageTag("en-US"));
            });

        });
    }

}
