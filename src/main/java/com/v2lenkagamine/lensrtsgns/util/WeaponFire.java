package com.v2lenkagamine.lensrtsgns.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

public class WeaponFire {

    public static void fireNormal(PlayerEntity playerIn,int range,int minDamage, int maxDamage) {
        double distance = Math.pow(range,2);
        int damage = (int) Math.round(ThreadLocalRandom.current().nextDouble(minDamage,maxDamage));
        Predicate<Entity> predicate = entity -> entity instanceof LivingEntity && !entity.isSpectator();
        HitResult result = didPlayerHitBlock(playerIn,distance);
        Vec3d look = playerIn.getRotationVector();
        Vec3d start = new Vec3d(playerIn.getX(), playerIn.getEyeY(), playerIn.getZ());
        Vec3d end = new Vec3d(playerIn.getX() + look.x * distance, playerIn.getEyeY() + look.y * distance, playerIn.getZ() + look.z * distance );
        if (result.getType() != HitResult.Type.MISS){
            end = result.getPos();
        }
        Box box = playerIn.getBoundingBox();
        EntityHitResult hitResult = ProjectileUtil.getEntityCollision(playerIn.world,playerIn,start,end,box.stretch(look),predicate);
        if(hitResult != null) {
            hitResult.getEntity().damage(DamageSource.player(playerIn),damage);
        }
    }
    public static void fireNormal(PlayerEntity playerIn,int range,int damage) {
        double distance = Math.pow(range,2);
        Predicate<Entity> predicate = entity -> entity instanceof LivingEntity && !entity.isSpectator();
        HitResult result = didPlayerHitBlock(playerIn, distance);
        Vec3d look = playerIn.getRotationVector();
        Vec3d start = new Vec3d(playerIn.getX(), playerIn.getEyeY(), playerIn.getZ());
        Vec3d end = new Vec3d(playerIn.getX() + look.x * distance, playerIn.getEyeY() + look.y * distance, playerIn.getZ() + look.z * distance );
        if (result.getType() != HitResult.Type.MISS){
            end = result.getPos();
        }
        Box box = playerIn.getBoundingBox();
        EntityHitResult hitResult = ProjectileUtil.getEntityCollision(playerIn.world,playerIn,start,end,box.stretch(look),predicate);
        if(hitResult != null) {
            hitResult.getEntity().damage(DamageSource.player(playerIn),damage);
        }
    }

    public static void firePierce(PlayerEntity playerIn,int range,int minDamage, int maxDamage) {
        double distance = Math.pow(range,2);
        int damage = (int) Math.round(ThreadLocalRandom.current().nextDouble(minDamage,maxDamage));
        Predicate<Entity> predicate = entity -> entity instanceof LivingEntity && !entity.isSpectator();
        HitResult result = didPlayerHitBlock(playerIn,distance);
        Vec3d look = playerIn.getRotationVector();
        Vec3d start = new Vec3d(playerIn.getX(), playerIn.getEyeY(), playerIn.getZ());
        Vec3d end = new Vec3d(playerIn.getX() + look.x * distance, playerIn.getEyeY() + look.y * distance, playerIn.getZ() + look.z * distance );
        if (result.getType() != HitResult.Type.MISS){
            end = result.getPos();
        }
        Box box = playerIn.getBoundingBox();
        List<EntityHitResult> hitResults = rayTraceEntityList(playerIn.world,playerIn,start,end,box.stretch(look),predicate);
        for (EntityHitResult hitResult : hitResults) {
            if (hitResult != null) {
                hitResult.getEntity().damage(DamageSource.player(playerIn), damage);
            }
        }
    }

    public static void firePierce(PlayerEntity playerIn,int range,int damage) {
        double distance = Math.pow(range, 2);
        Predicate<Entity> predicate = entity -> entity instanceof LivingEntity && !entity.isSpectator();
        HitResult result = didPlayerHitBlock(playerIn,distance);
        Vec3d look = playerIn.getRotationVector();
        Vec3d start = new Vec3d(playerIn.getX(), playerIn.getEyeY(), playerIn.getZ());
        Vec3d end = new Vec3d(playerIn.getX() + look.x * distance, playerIn.getEyeY() + look.y * distance, playerIn.getZ() + look.z * distance);
        if (result.getType() != HitResult.Type.MISS) {
            end = result.getPos();
        }
        Box box = playerIn.getBoundingBox();
        List<EntityHitResult> hitResults = rayTraceEntityList(playerIn.world, playerIn, start, end, box.stretch(look), predicate);
        for (EntityHitResult hitResult : hitResults) {
            if (hitResult != null) {
                hitResult.getEntity().damage(DamageSource.player(playerIn), damage);
            }
        }
    }


    //Util for weaponfire
    public static HitResult didPlayerHitBlock(Entity entIn,Double maxDistance) {
        Vec3d vec3d = entIn.getRotationVector();
        World world = entIn.world;
        Vec3d vec3dA = entIn.getCameraPosVec(1F);
        Vec3d vec3dB = vec3dA.add(vec3d.x * maxDistance,vec3d.y * maxDistance, vec3d.z * maxDistance);
        HitResult hitres = world.raycast(new RaycastContext(vec3dA,vec3dB, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE,entIn));
        if(hitres.getType() != HitResult.Type.MISS) {
            vec3dB = hitres.getPos();
        }
        return hitres;
    }


    public static List<EntityHitResult> rayTraceEntityList(World worldIn,Entity projectile, Vec3d start, Vec3d end, Box box,Predicate<Entity> filter) {
        List<EntityHitResult> entityRaytraceList = new ArrayList<>();
        for (Entity entity1 : worldIn.getOtherEntities(projectile,box,filter)) {
            Box theBox = entity1.getBoundingBox().expand(0.3f);
            Optional<Vec3d> optional = theBox.raycast(start, end);
            if(optional.isPresent()) {
                entityRaytraceList.add(new EntityHitResult(entity1));
            }
        }
        return entityRaytraceList;
    }
}
