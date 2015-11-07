package com.test.reflectiondemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class MainActivity extends Activity {
        private EditText one, two;
        private TextView result;
        private Button add, cut, ride, Except, sum;
        int first, second;
        String operaionFun = "";

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            findview();
            add.setOnClickListener(click);
            cut.setOnClickListener(click);
            ride.setOnClickListener(click);
            Except.setOnClickListener(click);
            sum.setOnClickListener(click);
        }

        void findview() {
            one = (EditText) findViewById(R.id.EditText01);
            two = (EditText) findViewById(R.id.EditText02);
            result = (TextView) findViewById(R.id.TextView01);
            add = (Button) findViewById(R.id.Button01);
            cut = (Button) findViewById(R.id.Button02);
            ride = (Button) findViewById(R.id.Button03);
            Except = (Button) findViewById(R.id.Button04);
            sum = (Button) findViewById(R.id.Button05);
        }

        View.OnClickListener click = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                first = Integer.parseInt(one.getText().toString());
                second = Integer.parseInt(two.getText().toString());
                switch (v.getId()) {
                    case R.id.Button01:
                        operaionFun = "+";
                        break;

                    case R.id.Button02:
                        operaionFun = "-";
                        break;
                    case R.id.Button03:
                        operaionFun = "*";
                        break;
                    case R.id.Button04:
                        operaionFun = "/";
                        break;
                    case R.id.Button05:
                        try {
                            result.setText(operation(operaionFun, first, second));
                        } catch (SecurityException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IllegalArgumentException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        break;
                }
            }
        };

        /**
         * 操作方法
         *
         * @param oper
         * @param first
         * @param second
         * @return
         * @throws ClassNotFoundException
         * @throws IllegalAccessException
         * @throws InstantiationException
         * @throws SecurityException
         * @throws NoSuchMethodException
         * @throws IllegalArgumentException
         * @throws InvocationTargetException
         */
        String operation(String oper, int first, int second)
                throws ClassNotFoundException, IllegalAccessException,
                InstantiationException, SecurityException, NoSuchMethodException,
                IllegalArgumentException, InvocationTargetException {
            // 获取相应的类对象名称
            Class<?> classType = Class.forName("com.test.reflectiondemo.OperationClass");

            // 如果知道类名并且类名存在于我们工程中，即jar 文件中包含可以使用如下写法
            //Class<?> classType = operationClass.class;
            // 返回本类对象
            Object invokeOperation = classType.newInstance();

            if (oper.equals("+")) {
                // 根据类对象名称去查找对应的方法
                Method addMethod = classType.getMethod("add", new Class[] {
                        int.class, int.class });
                // 调用查找 到的方法执行此方法的处理
                Object result = addMethod.invoke(invokeOperation, new Object[] {
                        new Integer(first), new Integer(second) });
                return result.toString();
            } else if (oper.equals("-")) {
                Method cutMethod = classType.getMethod("cut", new Class[] {
                        int.class, int.class });
                Object result = cutMethod.invoke(invokeOperation, new Object[] {
                        new Integer(first), new Integer(second) });
                return result.toString();
            } else if (oper.equals("*")) {
                Method rideMethod = classType.getMethod("ride", new Class[] {
                        int.class, int.class });
                Object result = rideMethod.invoke(invokeOperation, new Object[] {
                        new Integer(first), new Integer(second) });
                return result.toString();
            } else if (oper.equals("/")) {
                Method execMthod = classType.getMethod("Except", new Class[] {
                        int.class, int.class });
                Object result = execMthod.invoke(invokeOperation, new Object[] {
                        new Integer(first), new Integer(second) });
                return result.toString();
            }
            return "";
        }
    }
