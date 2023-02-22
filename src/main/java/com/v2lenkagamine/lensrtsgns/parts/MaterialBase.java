package com.v2lenkagamine.lensrtsgns.parts;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

public class MaterialBase {

    private final String materialName;
    private final String materialTag;
    private final double minDamageMod;
    private final double maxDamageMod;
    private final double fireRateMod;
    private final double reloadMod;
    private final int magSizeMod;
    private final double rangeMod;
    private final List<String> traitList;
    public MaterialBase(String materialName, String materialTag, double minDamageMod, double maxDamageMod, double fireRateMod, double reloadMod, int magSizeMod, double rangeMod, List<String> traitList) {
        this.materialName = materialName;
        this.materialTag = materialTag;
        this.minDamageMod = minDamageMod;
        this.maxDamageMod = maxDamageMod;
        this.fireRateMod = fireRateMod;
        this.reloadMod = reloadMod;
        this.magSizeMod = magSizeMod;
        this.rangeMod = rangeMod;
        this.traitList = traitList;
    }
    public String getMaterialName() {
        return this.materialName;
    }
    public String getMaterialTag() {return this.materialTag;}
    public double getMinDamageMod() {
        return this.minDamageMod;
    }
    public double getMaxDamageMod() {
        return this.maxDamageMod;
    }
    public double getFireRateMod() {
        return this.fireRateMod;
    }
    public double getReloadMod() {
        return this.reloadMod;
    }
    public int getMagSizeMod() {
        return this.magSizeMod;
    }
    public double getRangeMod() {
        return this.rangeMod;
    }

    public List<String> getTraitList() {return this.traitList;}

    public static final Codec<MaterialBase> MATERIAL_BASE_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("materialName").forGetter(MaterialBase::getMaterialName),
            Codec.STRING.fieldOf("materialTag").forGetter(MaterialBase::getMaterialTag),
            Codec.DOUBLE.fieldOf("minDamageMod").forGetter(MaterialBase::getMinDamageMod),
            Codec.DOUBLE.fieldOf("maxDamageMod").forGetter(MaterialBase::getMaxDamageMod),
            Codec.DOUBLE.fieldOf("fireRateMod").forGetter(MaterialBase::getFireRateMod),
            Codec.DOUBLE.fieldOf("reloadMod").forGetter(MaterialBase::getReloadMod),
            Codec.INT.fieldOf("magSizeMod").forGetter(MaterialBase::getMagSizeMod),
            Codec.DOUBLE.fieldOf("rangeMod").forGetter(MaterialBase::getRangeMod),
            Codec.STRING.listOf().fieldOf("traits").forGetter(MaterialBase::getTraitList)

    ).apply(instance, (materialName1, materialTag1, minDamageMod1, maxDamageMod1, fireRateMod1, reloadMod1, magSizeMod1, rangeMod1, traitList1) -> new MaterialBase(materialName1, materialTag1, minDamageMod1, maxDamageMod1, fireRateMod1, reloadMod1, magSizeMod1, rangeMod1, traitList1)));

}
