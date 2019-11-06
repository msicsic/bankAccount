package bdd.steps;

import bdd.steps.MyStepdefs.COperation;
import bdd.steps.MyStepdefs.CStatement;
import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;
import io.cucumber.datatable.TableEntryTransformer;

import java.util.Locale;

public class Configurer implements TypeRegistryConfigurer {
    @Override
    public Locale locale() {
        return Locale.getDefault();
    }

    @Override
    public void configureTypeRegistry(TypeRegistry registry) {
        registry.defineDataTableType(new DataTableType(CStatement.class, (TableEntryTransformer<CStatement>) entry -> {
            var res = new CStatement();
            res.operation = entry.get("operation");
            res.amount = Integer.parseInt(entry.get("amount"));
            res.balance = Integer.parseInt(entry.get("balance"));
            return res;
        }));
        registry.defineDataTableType(new DataTableType(COperation.class, (TableEntryTransformer<COperation>) entry -> {
            var res = new COperation();
            res.operation = entry.get("operation");
            res.amount = Integer.parseInt(entry.get("amount"));
            return res;
        }));
    }
}