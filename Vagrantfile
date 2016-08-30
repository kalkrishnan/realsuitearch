# -*- mode: ruby -*-
# vi: set ft=ruby :

# Specify Vagrant version and Vagrant API version
Vagrant.require_version ">= 1.6.0"
VAGRANTFILE_API_VERSION = "2"

# Create and configure the VM(s)
Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|

  # Assign a friendly name to this host VM
  config.vm.hostname = "docker-host"

  # Skip checking for an updated Vagrant box
  config.vm.box_check_update = false

  # Always use Vagrant's default insecure key
  config.ssh.insert_key = false

  # Spin up a "host box" for use with the Docker provider
  # and then provision it with Docker
  config.vm.box = "realsuitearch"
  config.vm.synced_folder "C:/Users/krishkal/workspace/REALSuiteArch", "/home/vagrant/REALSuiteArch"
  # The url from where the 'config.vm.box' box will be fetched if it
  # doesn't already exist on the user's system.
   config.vm.box_url = "https://github.com/tommy-muehle/puppet-vagrant-boxes/releases/download/1.1.0/centos-7.0-x86_64.box"
   config.vm.network :forwarded_port, guest: 8081, host: 8081
   #config.vm.network :forwarded_port, guest: 8080, host: 8080
     
	config.vm.provision :shell do |shell|
		begin 
    		shell.inline = "echo removing docker containers**;
    				docker stop $(docker ps -a -q);
					docker rm $(docker ps -a -q);
					echo done removing docker containers**"
		rescue
			puts "docker not yet installed"
		end
  	end
   config.vm.provision "shell", inline:
     "ls -LR;sudo iptables -F"
  config.vm.provision "docker" do |d|
	 d.build_image "/home/vagrant/REALSuiteArch", args: "-t 'realsuitearch'"
	 d.run "realsuitearch", args: " -p '8081:8081'"
  end

    # "ps aux | grep 'sshd:' | awk '{print $2}' | xargs kill"
  # Disable synced folders (prevents an NFS error on "vagrant up")
  #config.vm.synced_folder ".", "/vagrant", disabled: true

end