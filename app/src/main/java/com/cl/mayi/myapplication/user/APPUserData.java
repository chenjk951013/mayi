package com.cl.mayi.myapplication.user;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 用户基本数据
 *
 * @author Administrator
 */
public class APPUserData<T> {
    private Context act;
    Map<String, Object> objectMap;

    //private String classname;
    public APPUserData(Context act) {
        this.act = act;
        objectMap = new HashMap<>();
    }

    public synchronized void doSave(T bean) {
        //classname=Thread.currentThread() .getStackTrace()[1].getClassName();
        //dLog.e( Constant.TAG,"缓存-存："+bean.getClass().getSimpleName());
        SharedPreferences sharedPre = act.getSharedPreferences(bean.getClass().getSimpleName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        Class<? extends T> clazz = (Class<? extends T>) bean.getClass();
        Field[] arrFiled = clazz.getDeclaredFields();
        try {
            for (Field f : arrFiled) {
                f.setAccessible(true);

                if (!TYPES.containsKey(f.getType())) continue;
//                if (f.get(bean) == null) continue;

                int type = TYPES.get(f.getType());
                switch (type) {
                    case CLZ_BYTE:
                    case CLZ_SHORT:
                    case CLZ_INTEGER:
                        editor.putInt(f.getName(), f.getInt(bean));
                        break;
                    case CLZ_LONG:
                        editor.putLong(f.getName(), f.getLong(bean));
                        break;
                    case CLZ_STRING:
                        editor.putString(f.getName(), (String) f.get(bean));
                        break;
                    case CLZ_BOOLEAN:
                        editor.putBoolean(f.getName(), f.getBoolean(bean));
                        break;
                    case CLZ_FLOAT:
                        editor.putFloat(f.getName(), f.getFloat(bean));
                        break;
                    case CLZ_DOUBLE:
                        editor.putFloat(f.getName(), (float) f.getDouble(bean));
                        break;

                    case CLZ_LIST:
                        //  if (((Listq) f.get( bean )).size() != 0) {
                        //  editor.putString( f.getName(), ((Listq) f.get( bean )).get( 0 ).getClass().getSimpleName() );
                        editor.putInt(f.getName() + "size", doSaveList((List) f.get(bean), bean.getClass().getSimpleName()));
                        //   }
                        // editor.putStringSet(f.getName(), (Set<String>) f.get(bean));
                        break;
                    case CLZ_MAP:
                        editor.putStringSet(f.getName(), (Set<String>) f.get(bean));
                        break;
                }
            }

            editor.commit();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public T getModel(T bean) {

        SharedPreferences sharedPre = act.getSharedPreferences(bean.getClass().getSimpleName(), Context.MODE_PRIVATE);
        //  Class <T> bean= (Class<T>) entiy.getClass();
        Class<? extends T> clazz = (Class<? extends T>) bean.getClass();
        Field[] arrFiled = clazz.getDeclaredFields();

        try {
            for (Field f : arrFiled) {
                f.setAccessible(true);
                if (!TYPES.containsKey(f.getType())) continue;
                int type = TYPES.get(f.getType());
                switch (type) {
                    case CLZ_BYTE:
                        byte byteValue = (byte) sharedPre.getInt(f.getName(), 0);
                        f.set(bean, byteValue);
                        break;
                    case CLZ_SHORT:
                        short shortValue = (short) sharedPre.getInt(f.getName(), 0);
                        f.set(bean, shortValue);
                        break;
                    case CLZ_INTEGER:
                        int intValue = sharedPre.getInt(f.getName(), 0);
                        f.set(bean, intValue);
                        break;
                    case CLZ_LONG:
                        long longValue = sharedPre.getLong(f.getName(), 0L);
                        f.set(bean, longValue);
                        break;
                    case CLZ_STRING:
                        String str = sharedPre.getString(f.getName(), "");
                        f.set(bean, str);
                        //f.set
                        break;
                    case CLZ_BOOLEAN:
                        boolean bool = sharedPre.getBoolean(f.getName(), false);
                        f.set(bean, bool);
                        break;
                    case CLZ_FLOAT:
                        float floatValue = sharedPre.getFloat(f.getName(), 0.0f);
                        f.set(bean, floatValue);
                        break;
                    case CLZ_DOUBLE:
                        double doubleValue = sharedPre.getFloat(f.getName(), 0.0f);
                        f.set(bean, doubleValue);
                        break;
                    case CLZ_LIST:
                        Type fc = f.getGenericType(); // 关键的地方，如果是List类型，得到其Generic的类型
                        if (fc == null) continue;

                        if (fc instanceof ParameterizedType) // 【3】如果是泛型参数的类型
                        {
                            ParameterizedType pt = (ParameterizedType) fc;

                            Class genericClazz = (Class) pt.getActualTypeArguments()[0]; //【4】 得到泛型里的class类型对象。
                            //  f.set( bean, getList(genericClazz , sharedPre.getInt( (f.getName() + "size"), 0 ) ) );
                            f.set(bean, getList(genericClazz, sharedPre.getInt((f.getName() + "size"), 0), bean.getClass().getSimpleName()));

                            break;
                            // Method method = f.get( bean ).getClass().getMethod("get",null);
                            //        Class returnTypeClass =f.get( bean ).getClass();
                            //                                Lo g.e(Constant.TAG,sharedPre.getInt( (f.getName() + "size"),0)+" " +f.getName()+" "+f.getType()+" "+f.get( bean )) ;
                            // f.set( bean, getList(returnTypeClass , sharedPre.getInt( (f.getName() + "size"), 0 ) ) );


                            // double listValue = sharedPre.getFloat(f.getName(), 0.0f);
                            //   f.set(bean, getList( (Listq) f.get( bean ),sharedPre.getInt(f.getName(), 0) ));
                        }
                    case CLZ_MAP:
                        Set<String> mapValue = sharedPre.getStringSet(f.getName(), new HashSet<String>());

                        f.set(bean, mapValue);
                        break;
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return (T) bean;

    }

    public List getList(Class<?> clazzs, int size, String xname) {
        // 遍历集合
        int num = 0;
        List list = new ArrayList();
        for (int i = 0; i < size; i++) {
            try {
                list.add(clazzs.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }


        for (Object bean : list) {
            SharedPreferences sharedPre = act.getSharedPreferences(bean.getClass().getSimpleName() + xname + num, Context.MODE_PRIVATE);
            //  Class <T> bean= (Class<T>) entiy.getClass();
            Class<?> clazz = bean.getClass();
            Field[] arrFiled = clazz.getDeclaredFields();

            try {
                for (Field f : arrFiled) {
                    f.setAccessible(true);
                    if (!TYPES.containsKey(f.getType())) continue;
                    int type = TYPES.get(f.getType());
                    switch (type) {
                        case CLZ_BYTE:
                            byte byteValue = (byte) sharedPre.getInt(f.getName(), 0);
                            f.set(bean, byteValue);
                            break;
                        case CLZ_SHORT:
                            short shortValue = (short) sharedPre.getInt(f.getName(), 0);
                            f.set(bean, shortValue);
                            break;
                        case CLZ_INTEGER:
                            int intValue = sharedPre.getInt(f.getName(), 0);
                            f.set(bean, intValue);
                            break;
                        case CLZ_LONG:
                            long longValue = sharedPre.getLong(f.getName(), 0L);
                            f.set(bean, longValue);
                            break;
                        case CLZ_STRING:
                            String str = sharedPre.getString(f.getName(), "");
                            f.set(bean, str);
                            //f.set
                            break;
                        case CLZ_BOOLEAN:
                            boolean bool = sharedPre.getBoolean(f.getName(), false);
                            f.set(bean, bool);
                            break;
                        case CLZ_FLOAT:
                            float floatValue = sharedPre.getFloat(f.getName(), 0.0f);
                            f.set(bean, floatValue);
                            break;
                        case CLZ_DOUBLE:
                            double doubleValue = sharedPre.getFloat(f.getName(), 0.0f);
                            f.set(bean, doubleValue);
                            break;
                        case CLZ_LIST:


                            Type fc = f.getGenericType(); // 关键的地方，如果是List类型，得到其Generic的类型
                            if (fc == null) continue;

                            if (fc instanceof ParameterizedType) // 【3】如果是泛型参数的类型
                            {
                                ParameterizedType pt = (ParameterizedType) fc;

                                Class genericClazz = (Class) pt.getActualTypeArguments()[0]; //【4】 得到泛型里的class类型对象。
                                String xx = num + xname;
                                f.set(bean, getList(genericClazz, sharedPre.getInt((f.getName() + "size"), 0), xx));
                            }
                            break;
                        case CLZ_MAP:
                            Set<String> mapValue = sharedPre.getStringSet(f.getName(), new HashSet<String>());

                            f.set(bean, mapValue);
                            break;
                    }
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            num++;
        }
        return list;
    }

    public int doSaveList(List list, String xname) {

        if (list == null) return 0;
        int num = 0;
        try {
            // 遍历集合
            for (Object object : list) {

                SharedPreferences sharedPre = act.getSharedPreferences(object.getClass().getSimpleName() + xname + num, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPre.edit();
                Class clazz = object.getClass();// 获取集合中的对象类型
                Field[] fds = clazz.getDeclaredFields();// 获取他的字段数组
                for (Field f : fds) {// 遍历该数组
                    f.setAccessible(true);

                    if (!TYPES.containsKey(f.getType())) continue;


                    int type = TYPES.get(f.getType());
                    switch (type) {
                        case CLZ_BYTE:
                        case CLZ_SHORT:
                        case CLZ_INTEGER:
                            editor.putInt(f.getName(), f.getInt(object));
                            break;
                        case CLZ_LONG:
                            editor.putLong(f.getName(), f.getLong(object));
                            break;
                        case CLZ_STRING:
                            editor.putString(f.getName(), (String) f.get(object));
                            break;
                        case CLZ_BOOLEAN:
                            editor.putBoolean(f.getName(), f.getBoolean(object));
                            break;
                        case CLZ_FLOAT:
                            editor.putFloat(f.getName(), f.getFloat(object));
                            break;
                        case CLZ_DOUBLE:
                            editor.putFloat(f.getName(), (float) f.getDouble(object));
                            break;

                        case CLZ_LIST:
                            String xx = xname + num;
                            editor.putInt(f.getName(), doSaveList((List) f.get(object), xx));
                            // editor.putStringSet(f.getName(), (Set<String>) f.get(object));
                            break;
                        case CLZ_MAP:
                            editor.putStringSet(f.getName(), (Set<String>) f.get(object));
                            break;
                    }
                }

                editor.commit();
                num++;
            }
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //classname=Thread.currentThread() .getStackTrace()[1].getClassName();
        //dLog.e( Constant.TAG,"缓存-存："+bean.getClass().getSimpleName());

        return num;
    }

    //存储数据用
    public final int CLZ_BYTE = 1;
    public final int CLZ_SHORT = 2;
    public final int CLZ_INTEGER = 3;
    public final int CLZ_LONG = 4;
    public final int CLZ_STRING = 5;
    public final int CLZ_BOOLEAN = 6;
    public final int CLZ_FLOAT = 7;
    public final int CLZ_DOUBLE = 8;
    public final int CLZ_LIST = 9;
    public final int CLZ_MAP = 10;
    public final int CLZ_ENTIY = 11;
    public final Map<Class<?>, Integer> TYPES;

    {
        TYPES = new HashMap<Class<?>, Integer>();
        TYPES.put(byte.class, CLZ_BYTE);
        TYPES.put(short.class, CLZ_SHORT);
        TYPES.put(int.class, CLZ_INTEGER);
        TYPES.put(long.class, CLZ_LONG);
        TYPES.put(String.class, CLZ_STRING);
        TYPES.put(boolean.class, CLZ_BOOLEAN);
        TYPES.put(float.class, CLZ_FLOAT);
        TYPES.put(double.class, CLZ_DOUBLE);
        TYPES.put(List.class, CLZ_LIST);
        TYPES.put(Map.class, CLZ_MAP);
        //TYPES.put(T.class, CLZ_ENTIY);
    }

}
