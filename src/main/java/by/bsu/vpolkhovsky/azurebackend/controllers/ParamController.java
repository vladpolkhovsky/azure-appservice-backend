package by.bsu.vpolkhovsky.azurebackend.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class ParamController {

    private Map<String, String> values;

    @PostConstruct
    void init() {
        values = new HashMap<>();
    }

    @PostMapping(value = "/set/{var_name}")
    HttpEntity<String> setVariable(@PathVariable(value = "var_name") String varName, @RequestParam(value = "value", required = true) String varValue) {
        return new HttpEntity<>(Optional.ofNullable(values.put(varName, varValue)).orElse(varValue));
    }

    @GetMapping(value = "/get/{var_name}")
    HttpEntity<String> getVariable(@PathVariable(value = "var_name") String varName) {
        return new HttpEntity<>(Optional.ofNullable(values.get(varName)).orElse("null"));
    }

}
