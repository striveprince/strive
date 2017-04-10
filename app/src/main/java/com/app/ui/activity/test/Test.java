//package com.app.ui.activity.test;
//
///**
// * project：cutv_ningbo
// * description：
// * create developer： admin
// * create time：10:07
// * modify developer：  admin
// * modify time：10:07
// * modify remark：
// *
// * @version 2.0
// */
//
//
//public class Test {
//
//    public void getA(List<? extends B> list){//指定了b的子类作为泛型，所以T的类型可能是B,也可能是C
//        A a = new A();
//        B b = new B();
//        C c = new C();
//
//        list.setT(a);
//        list.setT(b);
//        list.setT(c);
//
//
//        A a1 = list.getT();
//        B b1 = list.getT();
//        C c1 = list.getT();
//    }
//
//    public void setA(List<? super B> list){ //指定了b的超类作为泛型，所以T的类型可能是A,B,也可能是I，还可能是Object
//        A a = new A();
//        B b = new B();
//        C c = new C();
//
//        list.setT(a);
//        list.setT(b);
//        list.setT(c);
//
//        A a1 = list.getT();
//        B b1 = list.getT();
//        C c1 = list.getT();
//    }
//
//    class List<T> {
//        T t;
//        public T getT() {
//            return t;
//        }
//
//        public void setT(T t) {
//            this.t = t;
//        }
//    }
//
//    interface I{}
//    class A{}
//    class B extends A implements I{}
//    class C extends B{}
//}
