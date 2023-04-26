package me.trolca.main.handlers;

import me.trolca.main.abstarcts.GameObject;
import me.trolca.main.abstarcts.Particle;
import me.trolca.main.enums.ID;
import me.trolca.main.objects.Trail;

import java.awt.*;
import java.util.ArrayList;

public class ParticleHandler {

    private ArrayList<Particle> particles;

    public ParticleHandler(){
        particles = new ArrayList<>();
    }

    public ArrayList<Particle> getParticles(){
        return particles;
    }

    public void addParticle(Particle particle){
        particles.add(particle);
    }

    public void removeParticle(Particle particle){
        particles.remove(particle);
    }

    public void clearTrails(){
        particles.removeIf(trail -> trail.getId() == ID.TRAIL);
    }


    public void tick(){

        for(int i=0; i < particles.size(); i++){
            particles.get(i).tick();
        }

    }

    public void render(Graphics g){

        for(int i=0; i < particles.size(); i++){
            particles.get(i).render(g);
        }

    }


}
