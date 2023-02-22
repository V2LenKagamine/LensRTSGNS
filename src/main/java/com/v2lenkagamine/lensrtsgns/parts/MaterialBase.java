package com.v2lenkagamine.lensrtsgns.parts;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class MaterialBase {

    private final String materialName;
    private final String materialTag;
    private final double minDamageMod;
    private final double maxDamageMod;
    private final double fireRateMod;
    private final double reloadMod;
    private final int magSizeMod;
    private final double rangeMod;
    public MaterialBase(String materialName,String materialTag, double minDamageMod, double maxDamageMod, double fireRateMod, double reloadMod, int magSizeMod, double rangeMod) {
        this.materialName = materialName;
        this.materialTag = materialTag;
        this.minDamageMod = minDamageMod;
        this.maxDamageMod = maxDamageMod;
        this.fireRateMod = fireRateMod;
        this.reloadMod = reloadMod;
        this.magSizeMod = magSizeMod;
        this.rangeMod = rangeMod;
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
    public static final Codec<MaterialBase> MATERIAL_BASE_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("materialName").forGetter(MaterialBase::getMaterialName),
            Codec.STRING.fieldOf("materialTag").forGetter(MaterialBase::getMaterialTag),
            Codec.DOUBLE.fieldOf("minDamageMod").forGetter(MaterialBase::getMinDamageMod),
            Codec.DOUBLE.fieldOf("maxDamageMod").forGetter(MaterialBase::getMaxDamageMod),
            Codec.DOUBLE.fieldOf("fireRateMod").forGetter(MaterialBase::getFireRateMod),
            Codec.DOUBLE.fieldOf("reloadMod").forGetter(MaterialBase::getReloadMod),
            Codec.INT.fieldOf("magSizeMod").forGetter(MaterialBase::getMagSizeMod),
            Codec.DOUBLE.fieldOf("rangeMod").forGetter(MaterialBase::getRangeMod)
    ).apply(instance,MaterialBase::new));

}
