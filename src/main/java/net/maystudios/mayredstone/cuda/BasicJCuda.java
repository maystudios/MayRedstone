package net.maystudios.mayredstone.cuda;

import jcuda.Pointer;
import jcuda.driver.*;

import net.maystudios.mayredstone.cuda.Vector3;


public class BasicJCuda {

    int result = 0;
    CUdevice device = new CUdevice();;
    CUcontext context = new CUcontext();
    CUmodule module = new CUmodule();
    CUfunction function = new CUfunction();


    long timeStart = 0;
    long timeEnd = 0;

    public BasicJCuda() {
        InitCountSelectContext_GPU();
        timeStart = System.currentTimeMillis();
    }

    public void InitCountSelectContext_GPU() {
        // Initialize JCuda
        int result = JCudaDriver.cuInit(0);
        if (result == CUresult.CUDA_SUCCESS)
        {
            System.out.println("JCuda initialization was successful");
        }
        else
        {
            System.out.println("JCuda initialization failed with error code " + result);
            return;
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
            return;
        }



        // Select the first device
        device = new CUdevice();
        result = JCudaDriver.cuDeviceGet(device, 0);
        if (result == CUresult.CUDA_SUCCESS)
        {
            System.out.println("Successfully selected device " + device);
        }
        else
        {
            System.out.println("cuDeviceGet failed with error code " + result);
            return;
        }



        result = JCudaDriver.cuCtxCreate(context,0, device);
        if (result == CUresult.CUDA_SUCCESS)
        {
            System.out.println("Successfully created CUDA context for device " + device);
        }
        else
        {
            System.out.println("cuCtxCreate failed with error code " + result);
            return;
        }
    }

    public void loadModule(String file) {
        // Load the CUDA module

        result = JCudaDriver.cuModuleLoad(module, file);
        if (result == CUresult.CUDA_SUCCESS)
        {
            System.out.println("Successfully loaded CUDA module");
        }
        else
        {
            System.out.println("cuModuleLoad failed with error code " + result);
            return ;
        }
    }

    public void loadFunction(String func) {
        // Get the function from the CUDA module

        result = JCudaDriver.cuModuleGetFunction(function,module, func);
        if (result == CUresult.CUDA_SUCCESS)
        {
            System.out.println("Successfully retrieved CUDA function");
        }
        else
        {
            System.out.println("cuModuleGetFunction failed with error code " + result);
            return;
        }
    }

    public void launchKernel(Pointer kernelParameters, CUstream hStream, Pointer extra, int sharedMemBytes, Vector3 gridDim, Vector3 blockDim) {
        result = JCudaDriver.cuLaunchKernel(function, gridDim.getX(), gridDim.getY(), gridDim.getZ(), blockDim.getX(), blockDim.getY(), blockDim.getZ(), sharedMemBytes, hStream, kernelParameters, extra);
        if (result == CUresult.CUDA_SUCCESS)
        {
            System.out.println("Successfully launched CUDA function");
        }
        else {
            System.out.println("Failed launched CUDA function with error code " + result);
            return;
        }
    }

    public void destroy() {
        JCudaDriver.cuModuleUnload(module);
        JCudaDriver.cuCtxDestroy(context);

        timeEnd = System.currentTimeMillis();
    }

    public long messureTime() {
        System.out.println("Verlaufszeit des Kernel: " + (timeEnd - timeStart) + " Millisek.");
        return timeEnd - timeStart;
    }
}
