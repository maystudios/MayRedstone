package net.maystudios.mayredstone.commands;

import jcuda.Pointer;
import jcuda.Sizeof;
import jcuda.driver.*;
import jcuda.runtime.JCuda;




public class JCudaArrayAddition {
    public String Add(int size) {

        String s = "";



        // Initialize JCuda
        int result = JCudaDriver.cuInit(0);
        if (result == CUresult.CUDA_SUCCESS)
        {
            System.out.println("JCuda initialization was successful");
        }
        else
        {
            System.out.println("JCuda initialization failed with error code " + result);
            return "";
        }



        // Get the number of CUDA-capable devices
        int deviceCount[] = { 0 };
        result = JCudaDriver.cuDeviceGetCount(deviceCount);
        if (result == CUresult.CUDA_SUCCESS)
        {
            System.out.println("Found " + deviceCount[0] + " CUDA-capable device(s)");
        }
        else
        {
            System.out.println("cuDeviceGetCount failed with error code " + result);
            return "";
        }



        // Select the first device
        CUdevice device = new CUdevice();
        result = JCudaDriver.cuDeviceGet(device, 0);
        if (result == CUresult.CUDA_SUCCESS)
        {
            System.out.println("Successfully selected device " + device);
        }
        else
        {
            System.out.println("cuDeviceGet failed with error code " + result);
            return "";
        }



        // Create a CUDA context for the selected device
        CUcontext context = new CUcontext();
        result = JCudaDriver.cuCtxCreate(context,0, device);
        if (result == CUresult.CUDA_SUCCESS)
        {
            System.out.println("Successfully created CUDA context for device " + device);
        }
        else
        {
            System.out.println("cuCtxCreate failed with error code " + result);
            return "";
        }



        // Load the CUDA module
        CUmodule module = new CUmodule();
        result = JCudaDriver.cuModuleLoad(module, "C:/Users/crazy/OneDrive/Desktop/MayRedstone/src/main/java/net/maystudios/mayredstone/commands/sampleKernel.ptx");
        if (result == CUresult.CUDA_SUCCESS)
        {
            System.out.println("Successfully loaded CUDA module");
        }
        else
        {
            System.out.println("cuModuleLoad failed with error code " + result);
            return "";
        }



        // Get the function from the CUDA module
        CUfunction function = new CUfunction();
        result = JCudaDriver.cuModuleGetFunction(function,module, "sampleKernel");
        if (result == CUresult.CUDA_SUCCESS)
        {
            System.out.println("Successfully retrieved CUDA function");
        }
        else
        {
            System.out.println("cuModuleGetFunction failed with error code " + result);
            return "";
        }



        // Alloziere Speicher auf GPU
        CUdeviceptr deviceInputA = new CUdeviceptr();
        JCudaDriver.cuMemAlloc(deviceInputA, 2 * Sizeof.INT);
        CUdeviceptr deviceInputB = new CUdeviceptr();
        JCudaDriver.cuMemAlloc(deviceInputB, 2 * Sizeof.INT);
        CUdeviceptr deviceOutput = new CUdeviceptr();
        JCudaDriver.cuMemAlloc(deviceOutput, 2 * Sizeof.INT);



        // Übertrage Daten von CPU zu GPU
        int[] hostInputA = new int[] {1, 2};
        JCudaDriver.cuMemcpyHtoD(deviceInputA, Pointer.to(hostInputA), 2 * Sizeof.INT);
        int[] hostInputB = new int[] {3, 4};
        JCudaDriver.cuMemcpyHtoD(deviceInputB, Pointer.to(hostInputB), 2 * Sizeof.INT);



        // Rufe CUDA-Kernel auf
        Pointer kernelParameters = Pointer.to(
                Pointer.to(deviceInputA),
                Pointer.to(deviceInputB),
                Pointer.to(deviceOutput));
        result = JCudaDriver.cuLaunchKernel(function, 1, 1, 1, 2, 1, 1, 0, null, kernelParameters, null);
        if (result == CUresult.CUDA_SUCCESS)
        {
            System.out.println("Successfully launched CUDA function");
        }
        else {
            System.out.println("Failed launched CUDA function with error code " + result);
            return "";
        }



        // Übertrage Ergebnisse zurück zur CPU
        int[] hostOutput = new int[2];
        JCudaDriver.cuMemcpyDtoH(Pointer.to(hostOutput), deviceOutput, 2 * Sizeof.INT);

        // Gebe Ergebnisse aus
        s = "Ergebnis: " + hostOutput[0] + " + " + hostOutput[1] + " = " + (hostOutput[0] + hostOutput[1]);

        int error = JCuda.cudaGetLastError();
        if(error != CUresult.CUDA_SUCCESS) {
            s = JCuda.cudaGetErrorString(error);
        }
        // Freigeben von Ressourcen
        JCudaDriver.cuMemFree(deviceInputA);
        JCudaDriver.cuMemFree(deviceInputB);
        JCudaDriver.cuMemFree(deviceOutput);
        JCudaDriver.cuModuleUnload(module);
        JCudaDriver.cuCtxDestroy(context);

        return s;
    }
}



