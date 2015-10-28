package cn.sharetop.maven.lejos_maven_plugin;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name="upload",defaultPhase=LifecyclePhase.PACKAGE)
public class EV3SCPUploadMojo extends AbstractMojo {

	@Parameter(defaultValue="/leJOS_EV3_0.9.0-beta/bin/ev3scpupload")
	private String commandTool;
	
	@Parameter(defaultValue="10.0.1.1")
	private String deviceName;
	
	@Parameter(defaultValue="/home/lejos/programs")
	private String targetPath;
	
	@Parameter(defaultValue="${project.build.finalName}.jar")
	private String sourceFile;
	
	@Parameter(defaultValue="${project.build.directory}")
	private String sourcePath;
	
	
	public void execute() throws MojoExecutionException, MojoFailureException {
		// TODO Auto-generated method stub
		getLog().info("sourceFile is "+sourceFile);
		
		String cmd = commandTool+" -n "+deviceName+" "+sourcePath+"/"+sourceFile+" "+targetPath+"/"+sourceFile;
		getLog().info(cmd);
		
		try {
			ProcessBuilder mb = new ProcessBuilder();
			mb.redirectErrorStream(true);
			
			Process p = mb.command(cmd.split(" ")).start();
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			String line=null;
			while((line=br.readLine())!=null){
				getLog().error(line);
			}
			br.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
