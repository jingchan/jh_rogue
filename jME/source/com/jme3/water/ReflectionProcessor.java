/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jme3.water;

import com.jme3.math.Plane;
import com.jme3.post.SceneProcessor;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.texture.FrameBuffer;

/**
 * Reflection Processor
 * Used to render the reflected scene in an off view port
 */
public class ReflectionProcessor implements SceneProcessor {

    private RenderManager rm;
    private ViewPort vp;
    private Camera reflectionCam;
    private FrameBuffer reflectionBuffer;
    private Plane reflectionClipPlane;

    public ReflectionProcessor(Camera reflectionCam, FrameBuffer reflectionBuffer, Plane reflectionClipPlane) {
        this.reflectionCam = reflectionCam;
        this.reflectionBuffer = reflectionBuffer;
        this.reflectionClipPlane = reflectionClipPlane;
    }

    public void initialize(RenderManager rm, ViewPort vp) {
        this.rm = rm;
        this.vp = vp;
    }

    public void reshape(ViewPort vp, int w, int h) {
    }

    public boolean isInitialized() {
        return rm != null;
    }

    public void preFrame(float tpf) {
    }

    public void postQueue(RenderQueue rq) {
        //we need special treatement for the sky because it must not be clipped
        rm.getRenderer().setFrameBuffer(reflectionBuffer);
        reflectionCam.setProjectionMatrix(null);
        rm.setCamera(reflectionCam, false);
        rm.getRenderer().clearBuffers(true, true, true);
        //Rendering the sky whithout clipping
        rm.getRenderer().setDepthRange(1, 1);
        vp.getQueue().renderQueue(RenderQueue.Bucket.Sky, rm, reflectionCam, true);
        rm.getRenderer().setDepthRange(0, 1);
        //setting the clip plane to the cam
        reflectionCam.setClipPlane(reflectionClipPlane, Plane.Side.Positive);//,1
        rm.setCamera(reflectionCam, false);

    }

    public void postFrame(FrameBuffer out) {
    }

    public void cleanup() {
    }

    public FrameBuffer getReflectionBuffer() {
        return reflectionBuffer;
    }

    public void setReflectionBuffer(FrameBuffer reflectionBuffer) {
        this.reflectionBuffer = reflectionBuffer;
    }

    public Camera getReflectionCam() {
        return reflectionCam;
    }

    public void setReflectionCam(Camera reflectionCam) {
        this.reflectionCam = reflectionCam;
    }

    public Plane getReflectionClipPlane() {
        return reflectionClipPlane;
    }

    public void setReflectionClipPlane(Plane reflectionClipPlane) {
        this.reflectionClipPlane = reflectionClipPlane;
    }
}
