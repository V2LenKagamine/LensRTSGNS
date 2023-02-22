package com.v2lenkagamine.lensrtsgns.datagen;

import com.v2lenkagamine.lensrtsgns.parts.MaterialBase;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;

import java.util.ArrayList;
import java.util.List;

public class LensMaterialGenerator extends LensMaterialProvider{

    protected LensMaterialGenerator(FabricDataOutput output) {
        super(output);
    }

    protected List<String> noTraits = new ArrayList<>();

    protected MaterialBase ironMaterial = new MaterialBase
            ("Iron","c:iron_ingots",1,1,-0.5,-0.5,0,1, noTraits);
    @Override
    protected void giveContext(ContextExporter exporter) {
        exporter.export("iron",ironMaterial);
    }
}
