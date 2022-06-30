public class Planet {
    /** variables */
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    static final double G=6.67e-11;

    public Planet(double xP,double yP,double xV,double yV,double m, String img){
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;
    }
    public Planet(Planet P){
        xxPos=P.xxPos;
        yyPos=P.yyPos;
        xxVel=P.xxVel;
        yyVel=P.yyVel;
        mass=P.mass;
        imgFileName=P.imgFileName;
    }
    public double calcDistance(Planet P){
        double dx=P.xxPos-xxPos;
        double dy=P.yyPos-yyPos;
        return Math.sqrt(dx*dx+dy*dy);
    }
    public double calcForceExertedBy(Planet P){
        double r=calcDistance(P);
        return G*P.mass*mass/(r*r);
    }
    public double calcForceExertedByX(Planet P){
        double dx=P.xxPos-xxPos;
        double ratio=dx/calcDistance(P);
        return calcForceExertedBy(P)*ratio;
    }
    public double calcForceExertedByY(Planet P){
        double dy=P.yyPos-yyPos;
        double ratio=dy/calcDistance(P);
        return calcForceExertedBy(P)*ratio;
    }
    public double calcNetForceExertedByX(Planet[] planets){
        int size= planets.length;
        double netForceX=0;
        for(Planet p:planets){
            if(p!=this){
                netForceX+=calcForceExertedByX(p);
            }
        }
        return netForceX;
    }
    public double calcNetForceExertedByY(Planet[] planets){
        int size= planets.length;
        double netForceY=0;
        for(int i=0;i<size;++i){
            if(planets[i]!=this){
                netForceY+=calcForceExertedByY(planets[i]);
            }
        }
        return netForceY;
    }

    public void update(double seconds,double xF,double yF){
        double ax=xF/mass;
        double ay=yF/mass;
        xxVel=xxVel+ax*seconds;
        yyVel=yyVel+ay*seconds;
        xxPos=xxPos+xxVel*seconds;
        yyPos=yyPos+yyVel*seconds;
    }
    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
    }

}
