package com.cucumber.france.transverse.test.auto.utility;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private Map<String, Object> scenarioContext;

    public ScenarioContext() {
        scenarioContext = new HashMap<>();
    }

    public void set(Context key, Object value) {
        scenarioContext.put(key.toString(), value);
    }

    public Object get(Context key){
        return scenarioContext.get(key.toString());
    }

    public boolean contains(Context key){
        return scenarioContext.containsKey(key.toString());
    }

    public void clear() {
        scenarioContext.clear();
    }

}
