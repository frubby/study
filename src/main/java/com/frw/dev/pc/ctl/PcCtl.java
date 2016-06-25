package com.frw.dev.pc.ctl;

import com.frw.dev.pc.pojo.PcMemory;
import org.apache.log4j.Logger;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PcCtl {
	Logger log=Logger.getLogger(PcCtl.class);
	@Autowired
	Sigar sigar;


	public PcMemory getPcMem(){
		try {
			Mem mem=sigar.getMem();
			log.info(mem.getTotal());
		} catch (SigarException e) {
			e.printStackTrace();
		}

		return null;

	}



}
