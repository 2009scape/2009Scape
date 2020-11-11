package core.tools;

import core.game.world.map.Location;

public class Vector3d {
    public double x;
    public double y;
    public double z;

    public Vector3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3d(double[] t) {
        this.x = t[0];
        this.y = t[1];
        this.z = t[2];
    }

    public Vector3d(Vector3d v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    public Vector3d(Location l) {
        this.x = l.getX();
        this.y = l.getY();
        this.z = l.getZ();
    }

    public Vector3d() {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }

    public final Vector3d add(Vector3d v1, Vector3d v2) {
        this.x = v1.x + v2.x;
        this.y = v1.y + v2.y;
        this.z = v1.z + v2.z;
        return this;
    }

    public final Vector3d add(Vector3d v1) {
        this.x += v1.x;
        this.y += v1.y;
        this.z += v1.z;
        return this;
    }

    public final Vector3d sub(Vector3d v1, Vector3d v2) {
        this.x = v1.x - v2.x;
        this.y = v1.y - v2.y;
        this.z = v1.z - v2.z;
        return this;
    }

    public final Vector3d sub(Vector3d v1) {
        this.x -= v1.x;
        this.y -= v1.y;
        this.z -= v1.z;
        return this;
    }

    public final Vector3d negate(Vector3d v1) {
        this.x = -v1.x;
        this.y = -v1.y;
        this.z = -v1.z;
        return this;
    }

    public final Vector3d negate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
        return this;
    }

    public final Vector3d cross(Vector3d v2) {
        this.cross(new Vector3d(this), v2);
        return this;
    }

    public final Vector3d cross(Vector3d v1, Vector3d v2) {
        this.x = v1.y*v2.z - v1.z*v2.y;
        this.y = v1.z*v2.x - v1.x*v2.z;
        this.z = v1.x*v2.y - v1.y*v2.x;
        return this;
    }

    public final double dot(Vector3d v2) {
        return (this.x*v2.x + this.y*v2.y + this.z*v2.z);
    }

    public final double l2norm() {
        return Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z);
    }

    public final Vector3d normalize() {
        double norm = this.l2norm();
        this.x /= norm;
        this.y /= norm;
        this.z /= norm;
        return this;
    }

    public static double signedAngle(final Vector3d v1, final Vector3d v2, final Vector3d n) {
        return Math.atan2(new Vector3d().cross(v1, v2).dot(n), v1.dot(v2));
    }

    public boolean equals(final Vector3d v1) {
        return (this.x == v1.x && this.y == v1.y && this.z == v1.z);
    }

    public boolean epsilonEquals(final Vector3d v1, double epsilon) {
        double diff;

        diff = this.x - v1.x;
        if (Double.isNaN(diff)) return false;
        if ((diff<0 ? -diff : diff) > epsilon) return false;

        diff = this.y - v1.y;
        if (Double.isNaN(diff)) return false;
        if ((diff<0 ? -diff : diff) > epsilon) return false;

        diff = this.z - v1.z;
        if (Double.isNaN(diff)) return false;
        if ((diff<0 ? -diff : diff) > epsilon) return false;

        return true;
    }
}
