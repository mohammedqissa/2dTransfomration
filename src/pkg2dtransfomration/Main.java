 
package pkg2dtransfomration;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class Main implements GLEventListener {

    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();


               gl.glColor3f(1f,1f,1f);
    
          gl.glBegin(GL2.GL_LINES);
           gl.glVertex3f(0.0f, 1f,0f);
            gl.glVertex3f(0.0f, -1f,0.0f);
            
                              
        gl.glEnd();
        
          gl.glBegin(GL2.GL_LINES);
           gl.glVertex3f(1f, 0f,0f);
            gl.glVertex3f(-1f, 0f,0.0f);
            
                              
        gl.glEnd();
       
       gl.glColor3f(1f,0f,0f);
        
	//The triangle
        gl.glBegin(GL2.GL_TRIANGLES);
            gl.glVertex2f(-0.8f, 0.8f);
            gl.glVertex2f(-0.4f, 0.4f);
            gl.glVertex2f(-0.8f, 0.2f);
        gl.glEnd();

        float []  r1=translation(-0.8f,0.8f, 1, 0);
         float []  r2=translation(-0.4f,0.4f, 1, 0);
         float []  r3=translation(-0.8f,0.2f, 1, 0);
        
           drawShape1(gl,GL2.GL_TRIANGLES,r1[0],r1[1],r2[0],r2[1],r3[0],r3[1]);
          float []  s1=scaling(-0.8f,0.8f, .5f, .5f,0f,0f);
          float []  s2=scaling(-0.4f,0.4f, .5f, .5f,0f,0f);
            float []  s3=scaling(-0.8f,0.2f, .5f, .5f,0f,0f);
            
                gl.glColor3f(1f,.1f,1f);   
            drawShape1(gl,GL2.GL_TRIANGLES,s1[0],s1[1],s2[0],s2[1],s3[0],s3[1]);
            float []  f1=translation(s1[0],s1[1],.2f,-.2f);
         float []  f2=translation(s2[0],s2[1], .2f, -0.2f);
         float []  f3=translation(s3[0],s3[1],.2f,-.2f);
        
           drawShape1(gl,GL2.GL_TRIANGLES,f1[0],f1[1],f2[0],f2[1],f3[0],f3[1]);
            
       float [] e= rotation(-.8f, .8f, -90, 0f, 0f);
       float [] w= rotation(-.4f, .4f, -90, 0f, 0f);
       float [] q= rotation(-.8f, .2f, -90, 0f, 0f);
        System.out.println(e[0]);
       System.out.println(e[1]);
         gl.glColor3f(0.7f,.9f,0.3f);   
        
     drawShape1(gl,GL2.GL_TRIANGLES,e[0],e[1],w[0],w[1],q[0],q[1]);
    }

    public void dispose(GLAutoDrawable arg0) {}

    public void init(GLAutoDrawable arg0) {}

    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {}

    public static void main(String[] args) {     
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        Main l = new Main();
        glcanvas.addGLEventListener(l);
        glcanvas.setSize(500, 500);
           
        final JFrame frame = new JFrame("");
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }
         

     public float[]  translation(float x,float y,float x_trans,float y_trans){
        
float []identityMatrix={x,y,1};
float [][]tran={{1,0,0},
                {0,1,0},
                {x_trans,y_trans,1}};
float [] res=new float[identityMatrix.length];
 
     
    for(int i=0;i<identityMatrix.length;i++){
        for(int j=0;j<identityMatrix.length;j++){
            res[i]+=tran[j][i]*identityMatrix[j];
        }
    }
    return res;
}
     
      public float[] scaling (float x,float y,float x_scale,float y_scale,float x_ref,float y_ref){
        //*(y_ref-y)
float []identityMatrix={x,y,1};
float [][]tran={{x_scale,0,0},
                {0,y_scale,0},
                {0,0,1}};
float [] res=new float[identityMatrix.length];


     
    for(int i=0;i<identityMatrix.length;i++){
        for(int j=0;j<identityMatrix.length;j++){
            res[i]+=tran[j][i]*identityMatrix[j];
          
        }
          if(i==0)res[i]+=x_ref;
            if(i==1)res[i]+=y_ref;
    }
    return res;
}
          public float[] rotation (float x,float y,double angle,float x_ref,float y_ref){
        angle=(angle*Math.PI)/180;
float []identityMatrix={x,y,1};
//float [] tran=translation(x, y, -x, -y);
float [][]rotate={{(float)Math.cos(angle),(float)Math.sin(angle),0},
                {(float)Math.sin(angle),(float)Math.cos(angle),0},
                {0,0,1}};
float [] res=new float[identityMatrix.length];
float out=0;

    
    for(int i=0;i<identityMatrix.length;i++){
        for(int j=0;j<identityMatrix.length;j++){
            res[i]+=rotate[j][i]*identityMatrix[j];
          
        }
         
    }
    return res;
}
          
      //    public float rotate
      public void drawShape1(GL2 g,int shape,float s1,float s2,float s3,float s4, float s5, float s6){
    g.glBegin(shape);
            g.glVertex2f(s1, s2);
            g.glVertex2f(s3, s4);
             g.glVertex2f(s5, s6);
        g.glEnd();
}   

}
 
