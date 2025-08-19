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
package org.eclipse.daanse.lcid.cfg;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.eclipse.daanse.lcid.api.LcidService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.osgi.util.converter.Converters;

/**
 * Default implementation of the {@link LcidService} interface.
 * <p>
 * This OSGi component provides LCID to Locale conversion functionality using the built-in language
 * identifier mappings.
 * </p>
 */
@Designate(ocd = Config.class, factory = true)
@Component(service = LcidService.class, scope = ServiceScope.SINGLETON, configurationPid = Constants.LCID_PID)
public class LcidServiceImpl implements LcidService {

    private static final Logger logger = LoggerFactory.getLogger(LcidServiceImpl.class);
    private Config config;

	@Activate
    public LcidServiceImpl(Map<String, Object> configMap) {
    	this.config = Converters.standardConverter().convert(configMap).to(Config.class);
    }

    @Deactivate
    public void deactivate(Map<String, Object> configMap) {
    	logger.debug("deactivated");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Locale> lcidToLocale(Optional<Integer> localeIdentifier) {
        try {
         	Locale locale = Locale.of(config.localeLanguage(), config.localeCountry(), config.localeVariant());
        	return Optional.of(locale);
        } catch (RuntimeException e) {
        	return Optional.empty();
        }
    }

}
