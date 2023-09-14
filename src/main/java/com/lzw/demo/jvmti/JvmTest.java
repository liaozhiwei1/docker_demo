package com.lzw.demo.jvmti;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import sun.tools.attach.HotSpotVirtualMachine;

import java.util.List;

public class JvmTest {
    public static void main(String[] args) throws Exception{
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor virtualMachineDescriptor : list) {
            if (virtualMachineDescriptor.displayName().contains("test")){
                HotSpotVirtualMachine attach = (HotSpotVirtualMachine) VirtualMachine.attach(virtualMachineDescriptor);
                attach.loadAgent("D:\\project\\docker_demo\\target\\demo-0.0.1-SNAPSHOT.jar");
                System.out.println(attach.getAgentProperties());
                attach.detach();
            }
        }
    }
}
