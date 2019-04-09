/*
 * This file is part of Titanium
 * Copyright (C) 2019, Horizon Studio <contact@hrznstudio.com>, All rights reserved.
 *
 * This means no, you cannot steal this code. This is licensed for sole use by Horizon Studio and its subsidiaries, you MUST be granted specific written permission by Horizon Studio to use this code, thinking you have permission IS NOT PERMISSION!
 */

package com.hrznstudio.titanium.event.handler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class EventManager {

    public static <T extends Event> FilteredEventManager<T> subscribe(Class<T> clazz) {
        return subscribe(clazz, EventPriority.NORMAL);
    }

    public static <T extends Event> FilteredEventManager<T> subscribe(Class<T> clazz, EventPriority priority) {
        FilteredEventManager<T> eventManager = new FilteredEventManager<T>(clazz);
        MinecraftForge.EVENT_BUS.addListener(priority, false, clazz, event -> {
            if (event.getClass().isAssignableFrom(clazz)) {
                if (eventManager.filter.test(event)) {
                    eventManager.process.accept(event);
                }
            }
        });
        return eventManager;
    }

    public static class FilteredEventManager<T extends Event> {
        private Predicate<T> filter;
        private Consumer<T> process;
        private Class<T> event;

        public FilteredEventManager(Class<T> clazz) {
            this.event = clazz;
            this.filter = t -> true;
            this.process = t -> {
            };
        }

        public FilteredEventManager<T> filter(Predicate<T> filter) {
            this.filter = filter;
            return this;
        }

        public void process(Consumer<T> consumer) {
            this.process = consumer;
        }
    }
}