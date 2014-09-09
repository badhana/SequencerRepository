package com.amazonws.codesamples.examples;

final class Demo
{
    private final int x;
    private static final int z;  //must be initialized here.

    static 
    {
        z = 10;  //It can be initialized here.
    }

    public Demo(int x)
    {
        this.x=x;  //This is possible.
        //z=15; //compiler-error - can not assign a value to a final variable z
    }
    
    public static void main(String[] args) {
		Demo demo = new Demo(15);
		//demo.z=20;
		System.out.println(demo.x +" "+demo.z);
	}
}