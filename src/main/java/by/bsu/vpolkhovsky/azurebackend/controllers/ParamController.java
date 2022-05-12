package by.bsu.vpolkhovsky.azurebackend.controllers;

import by.bsu.vpolkhovsky.azurebackend.dto.Variable;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ParamController {

    private Map<String, String> values;

    @PostConstruct
    void init() {
        values = new HashMap<>();
    }

    @PostMapping(value = "api/set/{var_name}")
    HttpEntity<String> setVariable(@PathVariable(value = "var_name") String varName, @RequestParam(value = "value", required = true) String varValue) {
        return new HttpEntity<>(Optional.ofNullable(values.put(varName, varValue)).orElse(varValue));
    }

    @GetMapping(value = "api/get/{var_name}")
    HttpEntity<String> getVariable(@PathVariable(value = "var_name") String varName) {
        return new HttpEntity<>(Optional.ofNullable(values.get(varName)).orElse("null"));
    }

    @GetMapping(value = "api/all")
    HttpEntity<List<Variable>> getVariables() {

        List<Variable> collect = values.entrySet().stream().map(
                entry -> new Variable(entry.getKey(), entry.getValue())
        ).collect(Collectors.toList());

        return new HttpEntity<>(collect);
    }

}
