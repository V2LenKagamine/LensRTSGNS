package com.v2lenkagamine.lensrtsgns.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class WeaponFire {

    public static void fireNormal(Player playerIn,double distance,int minDamage, int maxDamage) {
        int damage = (int) Math.round(ThreadLocalRandom.current().nextDouble(minDamage,maxDamage));
        Predicate<Entity> predicate = entity -> entity instanceof LivingEntity && !entity.isSpectator();
        HitResult result = didPlayerHitBlock(playerIn,distance);
        Vec3 look = playerIn.getLookAngle().normalize();
        Vec3 start = playerIn.getEyePosition(1F);
        Vec3 end = new Vec3(playerIn.getX() + look.x * distance, playerIn.getEyeY() + look.y * distance, playerIn.getZ() + look.z * distance );
        if (result.getType() != HitResult.Type.MISS){
            end = result.getLocation();
        }
        AABB box = playerIn.getBoundingBox();
        EntityHitResult hitResult = ProjectileUtil.getEntityHitResult(playerIn.level,playerIn,start,end,box.expandTowards(look.scale(distance)),predicate);
        if(hitResult != null) {
            hitResult.getEntity().hurt(DamageSource.playerAttack(playerIn),damage);
        }
    }
    public static void fireNormal(Player playerIn,double distance,int damage) {
        Predicate<Entity> predicate = entity -> entity instanceof LivingEntity && !entity.isSpectator();
        HitResult result = didPlayerHitBlock(playerIn, distance);
        Vec3 look = playerIn.getLookAngle().normalize();
        Vec3 start = playerIn.getEyePosition(1F);
        Vec3 end = new Vec3(playerIn.getX() + look.x * distance, playerIn.getEyeY() + look.y * distance, playerIn.getZ() + look.z * distance );
        if (result.getType() != HitResult.Type.MISS){
            end = result.getLocation();
        }
        AABB box = playerIn.getBoundingBox();
        EntityHitResult hitResult = ProjectileUtil.getEntityHitResult(playerIn.level,playerIn,start,end,box.expandTowards(look.scale(distance)),predicate);
        if(hitResult != null) {
            hitResult.getEntity().hurt(DamageSource.playerAttack(playerIn),damage);
        }
    }

    public static void firePierce(Player playerIn,double distance,int minDamage, int maxDamage) {
        int damage = (int) Math.round(ThreadLocalRandom.current().nextDouble(minDamage,maxDamage));
        Predicate<Entity> predicate = entity -> entity instanceof LivingEntity && !entity.isSpectator();
        HitResult result = didPlayerHitBlock(playerIn,distance);
        Vec3 look = playerIn.getLookAngle().normalize();
        Vec3 start = playerIn.getEyePosition(1F);
        Vec3 end = new Vec3(playerIn.getX() + look.x * distance, playerIn.getEyeY() + look.y * distance, playerIn.getZ() + look.z * distance );
        if (result.getType() != HitResult.Type.MISS){
            end = result.getLocation();
        }
        AABB box = playerIn.getBoundingBox();
        List<EntityHitResult> hitResults = rayTraceEntityList(playerIn.level,playerIn,start,end,box.expandTowards(look.scale(distance)),predicate);
        for (EntityHitResult hitResult : hitResults) {
            if (hitResult != null) {
                hitResult.getEntity().hurt(DamageSource.playerAttack(playerIn), damage);
            }
        }
    }

    public static void firePierce(Player playerIn,double distance,int damage) {
        Predicate<Entity> predicate = entity -> entity instanceof LivingEntity && !entity.isSpectator();
        HitResult result = didPlayerHitBlock(playerIn,distance);
        Vec3 look = playerIn.getLookAngle().normalize();
        Vec3 start = playerIn.getEyePosition(1F);
        Vec3 end = new Vec3(playerIn.getX() + look.x * distance, playerIn.getEyeY() + look.y * distance, playerIn.getZ() + look.z * distance);
        if (result.getType() != HitResult.Type.MISS) {
            end = result.getLocation();
        }
        AABB box = playerIn.getBoundingBox();
        List<EntityHitResult> hitResults = rayTraceEntityList(playerIn.level, playerIn, start, end, box.expandTowards(look.scale(distance)), predicate);
        for (EntityHitResult hitResult : hitResults) {
            if (hitResult != null) {
                hitResult.getEntity().hurt(DamageSource.playerAttack(playerIn), damage);
            }
        }
    }


    //Util for weaponfire
    public static HitResult didPlayerHitBlock(Entity entIn,Double maxDistance) {
        Vec3 vec3d = entIn.getLookAngle().normalize();
        Level world = entIn.level;
        Vec3 vec3dA = entIn.getEyePosition(1F);
        Vec3 vec3dB = vec3dA.add(vec3d.x * maxDistance,vec3d.y * maxDistance, vec3d.z * maxDistance);
        HitResult hitres = world.clip(new ClipContext(vec3dA,vec3dB, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE,entIn));
        if(hitres.getType() != HitResult.Type.MISS) {
            vec3dB = hitres.getLocation();
        }
        return hitres;
    }


    public static List<EntityHitResult> rayTraceEntityList(Level worldIn,Entity projectile, Vec3 start, Vec3 end, AABB box,Predicate<Entity> filter) {
        List<EntityHitResult> entityRaytraceList = new ArrayList<>();
        for (Entity entity1 : worldIn.getEntities(projectile,box,filter)) {
            AABB theBox = entity1.getBoundingBox().inflate(0.3f);
            Optional<Vec3> optional = theBox.clip(start, end);
            if(optional.isPresent()) {
                entityRaytraceList.add(new EntityHitResult(entity1));
            }
        }
        return entityRaytraceList;
    }
}
