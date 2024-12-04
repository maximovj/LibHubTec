# -*- mode: ruby -*-
# vi: set ft=ruby :

# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.
Vagrant.configure("2") do |config|
  # The most common configuration options are documented and commented below.
  # For a complete reference, please see the online documentation at
  # https://docs.vagrantup.com.

  # Every Vagrant development environment requires a box. You can search for
  # boxes at https://vagrantcloud.com/search.
  
  # Establecer el nombre de la caja
  #config.vm.define "box-libhubtec"
  #config.vm.hostname = "abc"

  config.vm.box = "ubuntu/focal64"

  # Disable automatic box update checking. If you disable this, then
  # boxes will only be checked for updates when the user runs
  # `vagrant box outdated`. This is not recommended.
  config.vm.box_check_update = false

  # Configurar clave de acceso pública con ssh
  #config.ssh.insert_key = false

  # Configurar sistema de virtualbox
  config.vm.provider "virtualbox" do |vb|
      # Mostrar la vetana de logs
      vb.gui = true

      # Personaliza la cantidad de memoria y cpus
      vb.memory = "2080"
      vb.cpus = "4"
  end

  # Create a forwarded port mapping which allows access to a specific port
  # within the machine from a port on the host machine. In the example below,
  # accessing "localhost:8080" will access port 80 on the guest machine.
  # NOTE: This will enable public access to the opened port
  # config.vm.network "forwarded_port", guest: 80, host: 8080

  # Create a forwarded port mapping which allows access to a specific port
  # within the machine from a port on the host machine and only allow access
  # via 127.0.0.1 to disable public access
  # config.vm.network "forwarded_port", guest: 80, host: 8080, host_ip: "127.0.0.1"

  # Create a private network, which allows host-only access to the machine
  # using a specific IP.
  config.vm.network "private_network", ip: "192.168.33.99"

  # Create a public network, which generally matched to bridged network.
  # Bridged networks make the machine appear as another physical device on
  # your network.
  # config.vm.network "public_network"

  # Share an additional folder to the guest VM. The first argument is
  # the path on the host to the actual folder. The second argument is
  # the path on the guest to mount the folder. And the optional third
  # argument is a set of non-required options.
  # config.vm.synced_folder "../data", "/vagrant_data"

  # Disable the default share of the current code directory. Doing this
  # provides improved isolation between the vagrant box and your host
  # by making sure your Vagrantfile isn't accessible to the vagrant box.
  # If you use this you may want to enable additional shared subfolders as
  # shown above.
  config.vm.synced_folder "./", "/home/vagrant/workspace"

  # Provider-specific configuration so you can fine-tune various
  # backing providers for Vagrant. These expose provider-specific options.
  # Example for VirtualBox:
  
  config.vm.provider "virtualbox" do |vb|
    # Display the VirtualBox GUI when booting the machine
    vb.gui = true
  
    # Customize the amount of memory on the VM:
    vb.memory = "1924"
    vb.cpus = "4"
  end
  
  # View the documentation for the provider you are using for more
  # information on available options.

  # Enable provisioning with a shell script. Additional provisioners such as
  # Ansible, Chef, Docker, Puppet and Salt are also available. Please see the
  # documentation for more information about their specific syntax and use.
  # config.vm.provision "shell", inline: <<-SHELL
  #   apt-get update
  #   apt-get install -y apache2
  # SHELL

  # Actualizar repositorio la caja de Ubuntu 20.04LTS
  config.vm.provision "shell", inline: "sudo apt-get update -qq -y"

  # Instalar docker y descagar imagen de docker (node:16.20-slim)
  # *OJO* : Corre solo una vez usando `vagrant up`
  config.vm.provision "install-docker",
      type: "docker",
      images: ["mysql:5.7", "maven:3.6.3-openjdk-17-slim", "shinsenter/laravel:php8.2-alpine", "node:20.11.1-alpine", "axllent/mailpit"]

  #config.vm.provider "vmware_fusion" do |v|
  #    v.vmx["vhv.enable"] = "TRUE"
  #end

  # Instalar docker-compose
  # ver: https://github.com/docker/compose/releases
  # *OJO* : Corre solo una vez usando `vagrant up`
  config.vm.provision "install-docker-compose",
      type: "shell",
      inline: <<-SCRIPT
      sudo rm -rf /usr/local/bin/docker-compose
      sudo rm -rf /usr/bin/docker-compose
      sudo curl -SL https://github.com/docker/compose/releases/download/v2.29.2/docker-compose-linux-x86_64 -o /usr/local/bin/docker-compose
      sudo chmod +x /usr/local/bin/docker-compose
      sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
      docker-compose --version
      echo 'alias doc=docker-compose' >> ~/.bashrc
      echo 'alias doc-again="docker-compose stop && docker-compose rm --force && docker-compose build --no-cache && docker-compose up"' >> ~/.bashrc
      source ~/.bashrc
      SCRIPT

  # Instalar herramientas de desarrollo
  # *OJO* : Corre solo una vez usando `vagrant up`
  config.vm.provision "install-tools",
      type: "shell",
      inline: <<-SHELL
          apt-get update
          apt-get install -y vim nano curl wget tmux net-tools screenfetch zip unzip htop aptitude
          apt-get install -y dos2unix tar build-essential software-properties-common ufw fail2ban
          apt-get install -y gdb httpie jq netcat nmap tree openssh-client software-properties-common gnupg2
      SHELL

  # Ejecutar el proyecto actual con Docker y docker-compose
  # *OJO* : Siempre corre usando `vagrant up`
  config.vm.provision "run-workspace",
      type: "shell",
      path: "./vagrant.sh",
      run: "always"

  # Mostrar información de la caja
  # *OJO* : Corre solo una vez usando `vagrant up`
  config.vm.provision "show-information",
      type: "shell",
      inline: <<-SHELL
          screenfetch
          netstat -rn
          ss -tuln
          lsblk
          ip route
          ipconfig
          echo "==================================="
          echo "==================================="
          echo "Nombre de usuario:"
          whoami
          echo "Distribución de Linux y versión:"
          lsb_release -a
          echo "Versión del kernel:"
          uname -r
          echo "Memoria RAM:"
          free -h
          echo "Número de CPUs y detalles del procesador:"
          lscpu
          echo "Información de almacenamiento:"
          df -h
          echo "Puertos habilitados y servicios en escucha:"
          netstat -tuln
          echo "Interfaces de red y configuración:"
          ip a
          echo "Servicios habilitados y en ejecución:"
          systemctl list-units --type=service --state=running
      SHELL
end
