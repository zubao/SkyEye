/*
 * Created by renqingyou on 2018/12/01.
 * Copyright 2015－2019 Sensors Data Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pingan.pad.skyeye.plugin

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Opcodes


class SkyEyeAnalyticsSDKHookConfig {

    HashMap<String, HashMap<String, ArrayList<SkyEyeAnalyticsMethodCell>>> methodCells = new HashMap<>()

    void disableIMEI(String methodName) {
        def imei = new SkyEyeAnalyticsMethodCell('getIMEI', '(Landroid/content/Context;)Ljava/lang/String;', 'createGetIMEI')
        def deviceID = new SkyEyeAnalyticsMethodCell('getDeviceID', '(Landroid/content/Context;I)Ljava/lang/String;', 'createGetDeviceID')
        def imeiMethods = [imei, deviceID]
        def imeiMethodCells = new HashMap<String, ArrayList<SkyEyeAnalyticsMethodCell>>()
        imeiMethodCells.put("com/sensorsdata/analytics/android/sdk/util/SensorsDataUtils", imeiMethods)
        methodCells.put(methodName, imeiMethodCells)
    }

    void disableAndroidID(String methodName) {
        def androidID = new SkyEyeAnalyticsMethodCell('getAndroidID', '(Landroid/content/Context;)Ljava/lang/String;', 'createGetAndroidID')
        def androidIDMethods = [androidID]
        def androidIdMethodCells = new HashMap<String, ArrayList<SkyEyeAnalyticsMethodCell>>()
        androidIdMethodCells.put('com/sensorsdata/analytics/android/sdk/util/SensorsDataUtils', androidIDMethods)
        methodCells.put(methodName, androidIdMethodCells)
    }

    void disableLog(String methodName) {
        def info = new SkyEyeAnalyticsMethodCell('info', '(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V', "createSALogInfo")
        def printStackTrace = new SkyEyeAnalyticsMethodCell('printStackTrace', '(Ljava/lang/Exception;)V', "createPrintStackTrack")
        def sALogMethods = [info, printStackTrace]
        def sALogMethodCells = new HashMap<String, ArrayList<SkyEyeAnalyticsMethodCell>>()
        sALogMethodCells.put('com/sensorsdata/analytics/android/sdk/SALog', sALogMethods)
        methodCells.put(methodName, sALogMethodCells)
    }

    void disableJsInterface(String methodName) {
        def showUpWebView = new SkyEyeAnalyticsMethodCell("showUpWebView", '(Landroid/webkit/WebView;Lorg/json/JSONObject;ZZ)V', "createShowUpWebViewFour")
        def showUpX5WebView = new SkyEyeAnalyticsMethodCell("showUpX5WebView", '(Ljava/lang/Object;Lorg/json/JSONObject;ZZ)V', "createShowUpX5WebViewFour")
        def showUpX5WebView2 = new SkyEyeAnalyticsMethodCell("showUpX5WebView", '(Ljava/lang/Object;Z)V', "createShowUpX5WebViewTwo")
        def sensorsDataAPIMethods = [showUpWebView, showUpX5WebView, showUpX5WebView2]
        def sensorsDataAPIMethodCells = new HashMap<String, ArrayList<SkyEyeAnalyticsMethodCell>>()
        sensorsDataAPIMethodCells.put('com/sensorsdata/analytics/android/sdk/SensorsDataAPI', sensorsDataAPIMethods)
        methodCells.put(methodName, sensorsDataAPIMethodCells)
    }

    void disableMacAddress(String methodName) {
        def macAddress = new SkyEyeAnalyticsMethodCell('getMacAddress', '(Landroid/content/Context;)Ljava/lang/String;', 'createGetMacAddress')
        def macMethods = [macAddress]
        def macMethodCells = new HashMap<String, ArrayList<SkyEyeAnalyticsMethodCell>>()
        macMethodCells.put("com/sensorsdata/analytics/android/sdk/util/SensorsDataUtils", macMethods)
        methodCells.put(methodName, macMethodCells)
    }

    void disableCarrier(String methodName) {
        def carrier = new SkyEyeAnalyticsMethodCell('getCarrier', '(Landroid/content/Context;)Ljava/lang/String;', 'createGetCarrier')
        def macMethods = [carrier]
        def macMethodCells = new HashMap<String, ArrayList<SkyEyeAnalyticsMethodCell>>()
        macMethodCells.put("com/sensorsdata/analytics/android/sdk/util/SensorsDataUtils", macMethods)
        methodCells.put(methodName, macMethodCells)
    }

    //todo 扩展

    void createGetIMEI(ClassVisitor classVisitor, SkyEyeAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitLdcInsn("")
        mv.visitInsn(Opcodes.ARETURN)
        mv.visitMaxs(1, 1)
        mv.visitEnd()
    }

    void createGetAndroidID(ClassVisitor classVisitor, SkyEyeAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitLdcInsn("")
        mv.visitInsn(Opcodes.ARETURN)
        mv.visitMaxs(1, 1)
        mv.visitEnd()
    }

    void createSALogInfo(ClassVisitor classVisitor, SkyEyeAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitInsn(Opcodes.RETURN)
        mv.visitMaxs(0, 3)
        mv.visitEnd()
    }

    void createPrintStackTrack(ClassVisitor classVisitor, SkyEyeAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitInsn(Opcodes.RETURN)
        mv.visitMaxs(0, 1)
        mv.visitEnd()
    }

    void createShowUpWebViewFour(ClassVisitor classVisitor, SkyEyeAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PUBLIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitInsn(Opcodes.RETURN)
        mv.visitMaxs(0, 5)
        mv.visitEnd()
    }

    void createShowUpX5WebViewFour(ClassVisitor classVisitor, SkyEyeAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PUBLIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitInsn(Opcodes.RETURN)
        mv.visitMaxs(0, 5)
        mv.visitEnd()
    }

    void createShowUpX5WebViewTwo(ClassVisitor classVisitor, SkyEyeAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PUBLIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitInsn(Opcodes.RETURN)
        mv.visitMaxs(0, 3)
        mv.visitEnd()
    }

    void createGetMacAddress(ClassVisitor classVisitor, SkyEyeAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitLdcInsn("")
        mv.visitInsn(Opcodes.ARETURN)
        mv.visitMaxs(1, 1)
        mv.visitEnd()
    }

    void createGetCarrier(ClassVisitor classVisitor, SkyEyeAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitLdcInsn("")
        mv.visitInsn(Opcodes.ARETURN)
        mv.visitMaxs(1, 1)
        mv.visitEnd()
    }

    void createGetDeviceID(ClassVisitor classVisitor, SkyEyeAnalyticsMethodCell methodCell) {
        def mv = classVisitor.visitMethod(Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, methodCell.name, methodCell.desc, null, null)
        mv.visitCode()
        mv.visitLdcInsn("")
        mv.visitInsn(Opcodes.ARETURN)
        mv.visitMaxs(1, 1)
        mv.visitEnd()
    }

    //todo 扩展

}
