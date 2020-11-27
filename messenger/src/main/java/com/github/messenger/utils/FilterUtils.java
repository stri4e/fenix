package com.github.messenger.utils;

import infobip.api.model.omni.send.OmniResponseDetails;

import java.util.function.Predicate;

public class FilterUtils {

   public static Predicate<OmniResponseDetails> FAILURE = r -> {
        var name = r.getStatus().getGroupName();
        return !"PENDING".equals(name) && !"DELIVERED".equals(name);
    };

    public static Predicate<OmniResponseDetails> SUCCESS = r -> {
        var name = r.getStatus().getGroupName();
        return !"PENDING".equals(name) && !"DELIVERED".equals(name);
    };

}
