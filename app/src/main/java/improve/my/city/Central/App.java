package improve.my.city.Central;

import java.util.InputMismatchException;
import java.util.Scanner;

import improve.my.city.Enums.Districts;
import improve.my.city.Enums.Institutions;
import improve.my.city.Enums.IssueType;
import improve.my.city.Enums.Status;
import improve.my.city.Enums.Subtypes;
import improve.my.city.Exceptions.CannotCreateAccountException;
import improve.my.city.Exceptions.CannotFindReportException;
import improve.my.city.Exceptions.CannotFindUserException;
import improve.my.city.Exceptions.EmptyArrayException;
import improve.my.city.Exceptions.OutOfTheOptionsException;
import improve.my.city.Reports.Issue;
import improve.my.city.Reports.Report;
import improve.my.city.Reports.ReportList;
import improve.my.city.User.Citizen;
import improve.my.city.User.Employee;

public class App {
    
    public static void main(String [] args) throws OutOfTheOptionsException{
        Scanner input = new Scanner(System.in);
        int choice = 0,option =0, password;
        String cpf, id;
        boolean login = false,loginC = false,loginE = false;
        CitizenList citizens = new CitizenList();
        EmployeeList employees = new EmployeeList();
        ReportList reports = new ReportList();
        do{
            do{
                try{
            System.out.println("\n Selecione uma opção: ");
            System.out.println("1. Login.");
            System.out.println("2. Criar conta.");
            System.out.println("3. Encerrar programa.\n");
            choice = input.nextInt();
            if(choice > 3 || choice < 1 ){
                throw new OutOfTheOptionsException();
            }
                }
                catch (InputMismatchException x){
                    System.out.println("Input inválido! Por favor, insira só números. ");
                } catch( OutOfTheOptionsException e){
                    System.out.println(e);
                }
                
            if(choice == 2){
                input.nextLine();
                System.out.println("\nSelecione o tipo de usuário.");
                System.out.println("1. Cidadão");
                System.out.println("2. Funcionário\n");
                option = input.nextInt();
                if(option == 1){
                    try {
                        createCitizenAccount(input, citizens);
                    } catch (CannotCreateAccountException e) {   
                        System.out.println(e);
                    }
                }else{
                    try {
                        createEmployeeAccount(input, employees);
                    } catch (CannotCreateAccountException z) {
                        System.out.println(z);
                    }
                }
            }
            }while(choice == 2); 

        if(choice == 3){
            System.out.println("\n Encerrando Programa...");
        } else{
                do{ 
                    try{
                    option = 0;
                    System.out.println("\n Selecione o tipo de usuário para login.");
                    System.out.println("1. Cidadão");
                    System.out.println("2. Funcionário \n");
                    option = input.nextInt();
                    input.nextLine();
                    }
                    catch (InputMismatchException x){
                        System.out.println("Input inválido! Por favor, insira só números. ");
                    }
                    try{
                    switch(option){
                        case 1:
                            System.out.println("\nInforme sua identificação de cidadão (CPF): \n");
                            cpf = input.nextLine();
                            System.out.println("Informe sua senha: \n" );
                            password = input.nextInt(); 
                            input.nextLine();
                            try {
                                if(login(input, citizens, password, cpf)){
                                    loginC = true;
                                    citizenMenu(citizens.getUser(cpf), reports);
                                }else{
                                    loginC = false;
                                    System.out.println("Não foi possível fazer login! Identificação ou senha inválidos.\n");
                                }
                            } catch (CannotFindUserException e) {
                                System.out.println(e);
                                return;
                            }
                            break;
                        case 2:
                            System.out.println("\nInforme sua identificação de funcionário: \n");
                            id = input.nextLine();
                            System.out.println("Informe sua senha: \n");
                            password = input.nextInt();
                            input.nextLine();
                            try {
                                if(login(input, employees, id, password)){
                                    loginE = true;
                                    employeeMenu(input, reports, employees.getUser(id));
                                }else{
                                    loginE = false;
                                    System.out.println("Não foi possível fazer login! Identificação ou senha inválidos. \n");
                                }
                            } catch (CannotFindUserException e) {
                                System.out.println(e);
                                return;
                            }
                            break;
                        default:
                            throw new OutOfTheOptionsException();
                            }
                        }catch(OutOfTheOptionsException x){
                            System.out.println(x);
                            return;
                        }catch(InputMismatchException y){
                            System.out.println("Input inválido! Por favor, insira o tipo de dado equivalente:");
                            System.out.println("Identificação -> Letras,números e símbolos.");
                            System.out.println("Senha -> Apenas números. \n");
                            return;
                        }
                            
                
                    if(loginC == true ||loginE == true){
                        login = true;
                    }
                    } while(login != true);
                } 
            }while(choice != 3);
            input.close();
        }

    
        // sobrecarga do método login
    public static boolean login(Scanner input, CitizenList citizens, int password, String cpf) throws CannotFindUserException { 

            if(citizens.searchUser(cpf)){
                if(citizens.getUser(cpf).equals(null)){
                    throw new CannotFindUserException();
                }else{
                    if(citizens.getUser(cpf).getPassword() == password){
                        return true;
                    }else{
                        return false;
                    } 
                }
            }else{
                return false;
            }

        }

    public static boolean login(Scanner input, EmployeeList employees, String id, int password) throws CannotFindUserException{ 

        if(employees.searchUser(id)){
            if(employees.getUser(id).equals(null)){
                throw new CannotFindUserException();
            }else{
                if(employees.getUser(id).getPassword() == password){
                    return true;
                }else{
                    return false;
                } 
            }
        }else{
            return false;
        }         
    }

    public static void createCitizenAccount(Scanner input, CitizenList citizenList) throws OutOfTheOptionsException, CannotCreateAccountException{
        String name, cpf, address;
        int password, option = 0;
        CitizenList citizens = citizenList;
        try{
        input.nextLine();
        System.out.println("Informe seu nome: \n");
        name = input.nextLine();
        
        System.out.println("Informe sua identificação de cidadão (CPF): \n");
        cpf = input.nextLine();
        
        System.out.println("Informe seu endereço: \n");
        address = input.nextLine();
        
        System.out.println("Crie uma senha: \n");
        password = input.nextInt();
        
        }catch(InputMismatchException e){
            System.out.println("Input inválido! Por favor, insira o tipo de dado equivalente:");
            System.out.println("Nome -> Apenas letras.");
            System.out.println("Identificação -> Letras,números e símbolos.");
            System.out.println("Senha -> Apenas números. \n");
            return;
        }
        if(citizens.searchUser(cpf)){
            throw new CannotCreateAccountException();
        }else{
        Citizen citizen = new Citizen(name, password, cpf, address);
        try{
        System.out.println("\nSelecione um bairro");
        System.out.println("1. Caxangá");
        System.out.println("2. Dois Irmãos");
        System.out.println("3. Iputinga");
        System.out.println("4. Cordeiro");
        System.out.println("5. Engenho do Meio");
        System.out.println("6.  Várzea \n");
        option = input.nextInt();

        }catch(InputMismatchException e){
            System.out.println("Input inválido! Por favor, insira um número.");
        }
        try{

        switch(option){
            case 1:
                citizen.setResidence(Districts.Caxangá);
                break;
            case 2:
                citizen.setResidence(Districts.DoisIrmãos);
                break;
            case 3:
                citizen.setResidence(Districts.Iputinga);
                break;
            case 4:
                citizen.setResidence(Districts.Cordeiro);
                break;
            case 5:
                citizen.setResidence(Districts.EngenhoDoMeio);
                break;
            case 6:
                citizen.setResidence(Districts.Várzea);
                break;
            default:
            throw new OutOfTheOptionsException();      
        }
        }catch(OutOfTheOptionsException p){
            System.out.println(p);
            return;
        }
        citizens.addUser(citizen);
        }
    }

    public static void createEmployeeAccount(Scanner input, EmployeeList employeeList) throws OutOfTheOptionsException, CannotCreateAccountException{
        String name, id;
        int password, option;
        EmployeeList employees = employeeList;
        try{
        input.nextLine();
        System.out.println("Informe seu nome: \n");
        name = input.nextLine();
        
        System.out.println("Informe sua identicação de funcionário: \n");
        id = input.nextLine();
        
        System.out.println("Crie uma senha: \n");
        password = input.nextInt();
        }catch(InputMismatchException e){
            System.out.println("Input inválido! Por favor, insira o tipo de dado equivalente:");
            System.out.println("Nome -> Apenas letras.");
            System.out.println("Identificação -> Letras,números e símbolos.");
            System.out.println("Senha -> Apenas números. \n");
            return;
        }
        try{
        if(employees.searchUser(id)){
            throw new CannotCreateAccountException();
        }
        }catch( CannotCreateAccountException h){
            System.out.println(h);
            return;
        }
         if(employees.searchUser(id) == false){
            try{
                input.nextLine();
            System.out.println("\n Selecione a instituição da qual pertence: ");
            System.out.println("1. Polícia.");
            System.out.println("2. Corpo de Bombeiros.");
            System.out.println("3. Vigilância Sanitária.");
            System.out.println("4. Centro de Controle de Zoonoses.");
            System.out.println("5. Autarquia de Manutenção e Limpeza Urbana do Recife.");
            System.out.println("6. CTTU.");
            System.out.println("7. Celpe.");
            System.out.println("8. Compesa.");
            System.out.println("9. Limpa Brasil.");
            System.out.println("10. Associação do Protetores de Animais de Pernambuco.\n");
            option = input.nextInt();
                input.nextLine();
            }catch(InputMismatchException o){
                System.out.println("Input inválido! Por favor, insira um número.");
                return;
            }
            Employee employee = new Employee(id, name, password);

            try{
            switch(option){
                case 1:
                    employee.setInstitution(Institutions.Polícia);
                    break;
                case 2:
                    employee.setInstitution(Institutions.CorpoDeBombeiros);
                break;
                case 3:
                    employee.setInstitution(Institutions.VigilânciaSanitária);
                break;
                case 4:
                    employee.setInstitution(Institutions.CentroDeControleDeZoonoses);
                break;
                case 5:
                    employee.setInstitution(Institutions.AutarquiaDeManutençãoELimpezaUrbanaDoRecife);
                break;
                case 6:
                    employee.setInstitution(Institutions.CTTU);
                break;
                case 7:
                    employee.setInstitution(Institutions.Celpe);
                break;
                case 8:
                    employee.setInstitution(Institutions.Compesa);
                break;
                case 9:
                    employee.setInstitution(Institutions.LimpaBrasil);
                break;
                case 10:
                    employee.setInstitution(Institutions.AssociaçãoDosProtetoresDeAnimaisDePernambuco);
                break;
                default:
                    throw new OutOfTheOptionsException();
            }
            }catch (OutOfTheOptionsException w){
                System.out.println(w);
                return;
            }
            employees.addEmployee(employee);
        } 
    }


    public static void citizenMenu(Citizen citizen, ReportList report) throws OutOfTheOptionsException{
        Scanner input = new Scanner(System.in);
        int option =0;
        boolean choice = false;
        do{ 
            try{
            System.out.println("1. Feed");
            System.out.println("2. Atividade");
            System.out.println("3. Seus dados");
            System.out.println("4. Reportar Problema");
            System.out.println("5. Deslogar");
            option = input.nextInt();
            }
            catch(InputMismatchException k){
                System.out.println("Input inválido! Por favor, insira um número.\n");
                
            }

            try{
            switch(option){
                case 1:
                    try {
                        feed(report, citizen, input);
                    } catch (EmptyArrayException v) {
                        System.out.println(v);
                    }catch (CannotFindReportException c){
                        System.out.println(c);
                    }
                    break;
                case 2:
                    try {
                    atividade(citizen, report);
                    } catch (CannotFindReportException e) {
                        System.out.println(e);
                    }
                    break;
                case 3:
                    seusDados(citizen);
                    break;
                case 4:
                    reportarProblema(citizen, report);
                    break;
                case 5:
                    choice = true;
                    break;
                default:
                    throw new OutOfTheOptionsException();
            }
        }
        catch(OutOfTheOptionsException o){
            System.out.println(o);
        }
        }while(choice != true);

    }
    public static void employeeMenu(Scanner input, ReportList reports, Employee employee) throws OutOfTheOptionsException{
        int option = 0;
        do{ 
            try{
            option = 0;
            System.out.println("1. Feed");
            System.out.println("2. Processos arquivados");
            System.out.println("3. Processos em aberto");
            System.out.println("4. Deslogar \n");
            option = input.nextInt();
            input.nextLine();
            }
            catch(InputMismatchException q){
                System.out.println("Input inválido! Por favor, insira um número. \n");
            }
            try{
            switch(option){
                case 1:
                    try {
                        feedEmployee(reports, employee, input);
                    } catch (EmptyArrayException q) {
                        System.out.println(q);
                    } catch (CannotFindReportException l) {
                        System.out.println(l);
                    }
                    break;
                case 2:
                    try {
                        archivedProcesses(reports, input);
                    } catch (EmptyArrayException e1) {
                        System.out.println(e1);
                    }
                    break;
                case 3:
                    try {
                        try {
                            openedProcesses(reports, input);
                        } catch (EmptyArrayException g) {
                            System.out.println(g);
                        }
                    } catch (CannotFindReportException e) {
                        System.out.println(e);
                    }
                    break;
                case 4:
                    option = 4;
                    break;
                default:
                throw new OutOfTheOptionsException();
                
            }
        }catch (OutOfTheOptionsException b){
            System.out.println(b);
        }
        }while(option != 4);
    }
    public static void feedEmployee(ReportList reports, Employee employee, Scanner input) throws OutOfTheOptionsException, EmptyArrayException, CannotFindReportException{
        ReportList array;
        Report report;
        int option =0, i;
        String id;
        try{

        array = matches(employee, reports);
        if (array == null || array.getSize() == 0){
            throw new EmptyArrayException();
        }else{
            for(i = 0; i < array.getSize(); i++){
                System.out.println(array.toString(i));
            }
            System.out.println("\n");
        }
        }catch( EmptyArrayException j){
            System.out.println(j);
            return;
        }
        do{
            try{
            System.out.println("\nGostaria de reconhecer um problema?");
            System.out.println("1. Sim");
            System.out.println("2. Não \n");

            option = input.nextInt();
            input.nextLine();
            if(option < 1 || option > 2){
                throw new OutOfTheOptionsException();
            }
            } catch(InputMismatchException f){
                System.out.println("\n Input inválido! Por favor, insira um número.\n");
            } catch (OutOfTheOptionsException p){
                System.out.println(p);
            }
            try{
            if(option == 1){
                System.out.println("\nInforme o Id do problema: \n");
                id = input.nextLine();
                report = reports.searchReport(id);
                if(report ==  null||report.getId() == null){
                    throw new CannotFindReportException();
                }else{
                    report.setMonitor(employee.getInstitution());
                    report.getIssue().setStatus(Status.Reconhecido); 
                    System.out.println("Relato reconhecido!");
                }  
            }else{
                option =2;
            }
            } catch(InputMismatchException l){
                System.out.println("Input inválido! Por favor, insira um Id composto por letras, números e símbolos.");
            } catch(CannotFindReportException c){
                System.out.println(c);
            }
        }while(option == 1);
    }
      

    public static void archivedProcesses(ReportList reports, Scanner input) throws OutOfTheOptionsException, EmptyArrayException{
        ReportList array = new ReportList(); 
        int i,option = 0;
        System.out.println("\nEstes são os processos arquivados: \n");
        for(i=0;i < reports.getSize();i++){
            if(reports.getReport(i).getIssue().getStatus() == Status.Resolvido){
                array.addReport(reports.getReport(i));
            }
        }
        if(array.getSize() == 0 ||array == null){
            throw new EmptyArrayException();
        }else{
            for(i = 0; i < array.getSize(); i++){
                System.out.println(array.toString(i));
            }
        }
        do{

            try{
            System.out.println("\nGostaria de continuar visualizando? \n");
            System.out.println("1. Continuar visualizando");
            System.out.println("2. Retornar ao menu\n");
            option =input.nextInt();
            }catch (InputMismatchException l){
                System.out.println("Input inválido! Por favor, insira um número.");
            }
            try{
            if(option < 1 || option > 2){
                throw new OutOfTheOptionsException();
            }
            }catch (OutOfTheOptionsException o){
                System.out.println(o);
            }
            
            if(option == 1){
                for(i=0;i < reports.getSize();i++){
                    if(reports.getReport(i).getIssue().getStatus() == Status.Resolvido){
                        array.addReport(reports.getReport(i));
                    }
                }
                array.toString();
            }
        }while(option != 2);
    }

    public static void openedProcesses(ReportList reports, Scanner input) throws OutOfTheOptionsException, CannotFindReportException, EmptyArrayException{
        ReportList array = new ReportList();
        Report report;
        int i,choice =0, option =0;
        String id, data;
        try{
        System.out.println("\n Estes são os processos em aberto: \n");
        for(i = 0; i < reports.getSize(); i++){
            if(reports.getReport(i).getIssue().getStatus() == Status.Reconhecido ||
            reports.getReport(i).getIssue().getStatus() == Status.EmProgresso){
                array.addReport(reports.getReport(i));
            }
        }
        if(array.getSize() == 0|| array == null){
            throw new EmptyArrayException();
        }else{
            for(i = 0; i < array.getSize(); i++){
                System.out.println(array.toString(i));
            }
        }
        }catch(EmptyArrayException j){
            System.out.println(j);
            return;
        }
        do{
            choice = 0;
            try{
            System.out.println("\n1. Continuar visualizando");
            System.out.println("2. Mudar status do processo");
            System.out.println("3. Retornar ao menu \n");
            choice = input.nextInt();
            input.nextLine();
            }catch(InputMismatchException  k){
                System.out.println("Input inválido! Por favor, insira um número.");
            }

            try{
            switch(choice){
                case 1:
                    choice = 1;
                    break;
                case 2:
                    System.out.println("\nInforme o Id do processo: \n");
                    id = input.nextLine();
                    report = reports.searchReport(id);
                    if(report == null || report.getId() == null){
                        throw new CannotFindReportException();
                    }
                        
                    System.out.println("\nSelecione o novo status: ");
                    System.out.println("1. Em progresso");
                    System.out.println("2. Resolvido \n");
                    option = input.nextInt();
                    input.nextLine();

                    if(option == 1){
                        report.getIssue().setStatus(Status.EmProgresso);
                    }else{
                        report.getIssue().setStatus(Status.Resolvido);
                    }
                    System.out.println("\nInforme a data: \n");
                    data = input.nextLine();
                    report.setUpdate(data);
                    break;
                case 3:
                    choice = 3;
                    break;

                default:
                    throw new OutOfTheOptionsException();
                }
            }catch (InputMismatchException s){
                System.out.println("Input inválido! Por favor, insira um valor equivalente ao tipo de dado:");
                System.out.println("Id -> Letras, números e símbolos.");
                System.out.println("Opção -> Apenas números.");
            }catch (OutOfTheOptionsException t){
                System.out.println(t);
            }catch (CannotFindReportException c){
                System.out.println(c);
            }
        
        }while(choice != 3);
    }

    public static ReportList matches (Employee employee, ReportList reports){
        int i;
        ReportList array = new ReportList();
        for(i=0; i <reports.getSize(); i++){
            if(employee.getInstitution() == Institutions.Polícia){
                if(reports.getReport(i).getIssue().getSubtType() == Subtypes.AtividadeCriminal ||
                reports.getReport(i).getIssue().getSubtType() == Subtypes.PessoaDesaparecida ||
                reports.getReport(i).getIssue().getSubtType() == Subtypes.AbusoDeAnimal){
                    if(reports.getReport(i).getIssue().getStatus() == Status.Submetido){
                        array.addReport(reports.getReport(i));
                    }
                }
            }
            else if(employee.getInstitution() == Institutions.CorpoDeBombeiros){
                if( reports.getReport(i).getIssue().getSubtType() == Subtypes.Acidente ||
                reports.getReport(i).getIssue().getSubtType() == Subtypes.PossívelPerigo){
                    if(reports.getReport(i).getIssue().getStatus() == Status.Submetido){
                        if(reports.getReport(i).getIssue().getStatus() == Status.Submetido){
                            array.addReport(reports.getReport(i));
                        }
                    }
                }

            }
            else if(employee.getInstitution() == Institutions.VigilânciaSanitária){
                if( reports.getReport(i).getIssue().getSubtType() == Subtypes.ÁreaPoluída ||
                reports.getReport(i).getIssue().getSubtType() == Subtypes.Saneamento){
                    if(reports.getReport(i).getIssue().getStatus() == Status.Submetido){
                        array.addReport(reports.getReport(i));
                    }
                }
            }
            else if(employee.getInstitution() == Institutions.AutarquiaDeManutençãoELimpezaUrbanaDoRecife){
                if( reports.getReport(i).getIssue().getSubtType() == Subtypes.ColetaDeItemGrande ||
                reports.getReport(i).getIssue().getSubtType() == Subtypes.AnimalMorto||
                reports.getReport(i).getIssue().getSubtType() == Subtypes.DespejoIlegal||
                reports.getReport(i).getIssue().getSubtType() == Subtypes.EscoamentoEntupido ||
                reports.getReport(i).getIssue().getSubtType() == Subtypes.ÁreaPoluída ||
                reports.getReport(i).getIssue().getSubtType() == Subtypes.Buraco||
                reports.getReport(i).getIssue().getSubtType() == Subtypes.Rachadura){
                    if(reports.getReport(i).getIssue().getStatus() == Status.Submetido){
                        array.addReport(reports.getReport(i));
                    }
                }  
            }
            else if(employee.getInstitution() == Institutions.Compesa){
                if( reports.getReport(i).getIssue().getSubtType() == Subtypes.FaltaDeBueiro ||
                reports.getReport(i).getIssue().getSubtType() == Subtypes.VazamentoDeÁgua){
                    if(reports.getReport(i).getIssue().getStatus() == Status.Submetido){
                        array.addReport(reports.getReport(i));
                    }

                }
            }
            else if(employee.getInstitution() == Institutions.CTTU){
                if( reports.getReport(i).getIssue().getSubtType() == Subtypes.FaltaDeSinalização ||
                reports.getReport(i).getIssue().getSubtType() == Subtypes.Semáforo){
                    if(reports.getReport(i).getIssue().getStatus() == Status.Submetido){
                        array.addReport(reports.getReport(i));
                    }

                }
            }
            else if(employee.getInstitution() == Institutions.Celpe){
                if( reports.getReport(i).getIssue().getSubtType() == Subtypes.FaltaDeEnergia){
                    if(reports.getReport(i).getIssue().getStatus() == Status.Submetido){
                        array.addReport(reports.getReport(i));
                    }

                }
            }
            else if(employee.getInstitution() == Institutions.CentroDeControleDeZoonoses){
                if( reports.getReport(i).getIssue().getSubtType() == Subtypes.AnimalMorto){
                    if(reports.getReport(i).getIssue().getStatus() == Status.Submetido){
                        array.addReport(reports.getReport(i));
                    }

                }
            }
            else if(employee.getInstitution() == Institutions.AssociaçãoDosProtetoresDeAnimaisDePernambuco){
                if( reports.getReport(i).getIssue().getSubtType() == Subtypes.AbusoDeAnimal||
                reports.getReport(i).getIssue().getSubtType() == Subtypes.AnimalAbandonado){
                    if(reports.getReport(i).getIssue().getStatus() == Status.Submetido){
                        array.addReport(reports.getReport(i));
                    }

                }
            }
            else if(employee.getInstitution() == Institutions.LimpaBrasil){
                if( reports.getReport(i).getIssue().getSubtType() == Subtypes.ÁreaPoluída){
                    if(reports.getReport(i).getIssue().getStatus() == Status.Submetido){
                        array.addReport(reports.getReport(i));
                    }
                }
            }
        }
        return array;
    
    }

    public static void atividade(Citizen citizen, ReportList reports) throws  CannotFindReportException{
        int i;
        ReportList criados = new ReportList(); 
        ReportList confirmados = new ReportList();
        
            System.out.println("Relatos confirmados:\n ");
            for(i =0; i < citizen.getConfirmedSize(); i++){
                
                confirmados.addReport(reports.searchReport(citizen.getConfirmed(i)));
    
            }
            for(i = 0; i < confirmados.getSize();i++){
        
                System.out.println(confirmados.toString(i));  
            }
 
            System.out.println("Relatos criados:\n ");
            for(i =0; i < citizen.getCreatedSize(); i++){
                
                criados.addReport(reports.searchReport(citizen.getCreated(i)));
    
            }
            for(i = 0; i < criados.getSize();i++){
        
                System.out.println(criados.toString(i));  
            }    
        
    }
    
    public static void feed(ReportList reports, Citizen citizen, Scanner input) throws OutOfTheOptionsException, EmptyArrayException, CannotFindReportException{
        ReportList array = new ReportList();
        int option =0, i, choice =0;
        String id;
        do{
            try{
            System.out.println("\n1. Problemas ao seu redor.");
            System.out.println("2. Pessoas desaparecidas.");
            System.out.println("3. Perigos ao seu redor.");
            System.out.println("4. Sair do feed.\n");
            choice = input.nextInt();
            if(choice < 1 || choice > 4){
                throw new OutOfTheOptionsException();
            }
            } catch (InputMismatchException k){
                System.out.println("Input inválido! Por favor, insira um número.");
            } catch (OutOfTheOptionsException j){
                System.out.println(j);
            }
            try{
            switch(choice){
                case 1:
                    option =0;
                    array = problemasAoRedor(citizen, reports);
                    if(array == null || array.getSize() == 0){
                        throw new EmptyArrayException();
                    }else{
                        for(i = 0; i < array.getSize();i++){
                            System.out.println(array.toString(i));  
                        }

                    do{
                    System.out.println("Gostaria de confirmar um relato? ");
                    System.out.println("1. Sim");
                    System.out.println("2. Não");
                    option = input.nextInt();
                    input.nextLine();
                    if(option == 1){
                        System.out.println("Informe o Id do relato: ");
                        id = input.nextLine();
                        if(reports.searchReport(id) == null){
                            throw new CannotFindReportException();
                            // mostrar msg
                            
                        }else{
                            reports.searchReport(id).setVotes();
                            citizen.setConfirmados(id);
                            System.out.println("Relato Confirmado!");
                        }
                    }
                    }while(option == 1);
                    }

                    break;
                case 2:
                    array = pessoasDesaparecidas(reports);
                    if(array == null || array.getSize() == 0){
                        throw new EmptyArrayException();
                    }else{
                        for(i = 0; i < array.getSize();i++){
                            System.out.println(array.toString(i));  
                        }
                        do{
                        System.out.println("\n Gostaria de continuar visualizando ?");
                        System.out.println("1. Sim");
                        System.out.println("2. Não");
                        option = input.nextInt();
                        input.nextLine();
                        }while(option == 1);
                    }
                    break;
                case 3:
                    array = perigosAoRedor(citizen, reports);
                    if(array == null || array.getSize() == 0){
                        throw new EmptyArrayException();
                    }else{
                        for(i = 0; i < array.getSize();i++){
                            System.out.println(array.toString(i));  
                        }
                    do{
                        System.out.println("\n Gostaria de continuar visualizando ?");
                        System.out.println("1. Sim");
                        System.out.println("2. Não");
                        option = input.nextInt();
                        input.nextLine();
                        }while(option == 1);
                    }
                    break;
                    case 4:
                        return;
                default:
                    throw new OutOfTheOptionsException();
            }
        }catch(InputMismatchException f){
            System.out.println("Input inválido! Por favor, insira um valor equivalente ao tipo de dado:");
            System.out.println("Id -> Letras, números e símbolos.");
            System.out.println("Opção -> Apenas números.");
        }catch (OutOfTheOptionsException s){
            System.out.println(s);
        }catch (EmptyArrayException a){
            System.out.println(a);
        }catch (CannotFindReportException c){
            System.out.println(c);
        }
    }while(choice != 4);
    }

    public static ReportList pessoasDesaparecidas(ReportList reports){
        int i;
        ReportList array = new ReportList(); 
        for(i =0; i < reports.getSize(); i++){
            if(reports.getReport(i).getIssue().getSubtType().equals(Subtypes.PessoaDesaparecida)){
                array.addReport(reports.getReport(i));
                }
            }
            return array;
        }


    
    public static ReportList perigosAoRedor(Citizen citizen, ReportList reports){
        int i;
        ReportList array = new ReportList(); 
        for(i =0; i < reports.getSize(); i++){
            if(citizen.getResidence().equals(reports.getReport(i).getDistrict())){
                if(reports.getReport(i).getIssue().getSubtType() == Subtypes.PossívelPerigo){
                    array.addReport(reports.getReport(i));
                }
            }
        }
        return array;
    } 

    public static ReportList problemasAoRedor(Citizen citizen, ReportList reports){
        int i;
        ReportList array = new ReportList();
        for(i =0; i < reports.getSize(); i++){
            if(citizen.getResidence().equals(reports.getReport(i).getDistrict())){
                array.addReport(reports.getReport(i));
            }
        }
        return array;
    }

    public static void seusDados(Citizen citizen){
        System.out.println(citizen.toString());
    }
    
    public static void reportarProblema(Citizen citizen, ReportList reports) throws OutOfTheOptionsException{
        Scanner input = new Scanner(System.in);
        ReportList list = reports;
        int option = 0;
        do{  
            Issue issue = new Issue();
            Report report = new Report();
            option = 0;
            String description, data;
            tipoDeProblema(input, issue, report);
            try{
            System.out.println("\nDeseja adicionar descrição ?");
                System.out.println("1. Sim");
                System.out.println("2. Não\n");
                option = input.nextInt();
                input.nextLine();
                if(option < 1 ||option > 2){
                    throw new OutOfTheOptionsException();
                } 
                if(option == 1){
                    System.out.println("Descreva o problema: \n");
                    description = input.nextLine();
                    issue.setDescription(description);
                }
            } catch (InputMismatchException r){
                System.out.println("Input inválido! Por favor, insira um valor referente ao tipo de dado: ");
                System.out.println("Opção -> Apenas números.");
                System.out.println("Descrição -> Letras e números.");
            }catch(OutOfTheOptionsException x){
                System.out.println(x);
            }
            local(input, report);
            try{
            System.out.println("Informe a data. \n");
                data = input.nextLine();
                report.setDate(data);

            adicionarMidia(input, report);

            System.out.println("Deseja tornar o relato anônimo ?");
                option = 0;
                System.out.println("1. Sim");
                System.out.println("2. Não \n");
                option = input.nextInt();
                input.nextLine();
                if(option < 1 ||option > 2){
                    throw new OutOfTheOptionsException();
                } 
                }catch (InputMismatchException l){
                    System.out.println("Input inválido! Por favor, insira um valor referente ao tipo de dado: ");
                    System.out.println("Opção -> Apenas números.");
                    System.out.println("Data -> Números e /.");
                }catch(OutOfTheOptionsException b){
                    System.out.println(b);
                }
                if(option == 1){
                    report.setUser(null);
                }

                if(option == 2){
                report.setUser(citizen);
                }
                report.setIssue(issue);
                report.getIssue().setStatus(Status.Submetido);

                list.addReport(report);
                citizen.setCriados(report.getId());
                try{
                option = 0;
                System.out.println("Gostaria de reportar outro problema?");
                System.out.println("1. Sim");
                System.out.println("2. Não \n");
                option = input.nextInt();
                if(option < 1 ||option > 2){
                    throw new OutOfTheOptionsException();
                } 
                }catch (InputMismatchException l){
                    System.out.println("Input inválido! Por favor, insira um número dentre os listados.");
                }catch(OutOfTheOptionsException b){
                    System.out.println(b);
                }
        }while(option == 1);
        
    }

    public static int tipoDeProblema(Scanner input, Issue issue, Report report) throws OutOfTheOptionsException{
        int option = 0;
        try{
        System.out.println("\nSelecione o tipo e problema");
        System.out.println("1. Infraestura");
        System.out.println("2. Saneamento");
        System.out.println("3. Segurança");
        System.out.println("4. Animal\n");
        option = input.nextInt();
        input.nextLine();
        if(option < 1 || option > 4){
            throw new OutOfTheOptionsException();
        }
        switch(option){
            case 1:
                issue.setIssueType(IssueType.Infraestrutura);
                problemaInfraestrutura(issue, input);
                break;
            case 2:
                issue.setIssueType(IssueType.Lixo);
                problemaLixo(issue, input);
                break;
            case 3:
                issue.setIssueType(IssueType.Segurança);
                problemaSeguranca(issue, input);
                break;
            case 4:
                issue.setIssueType(IssueType.Animal);
                problemaAnimal(input, issue);
                break;
            default:
                throw new OutOfTheOptionsException();
        }
        } catch (InputMismatchException z){
            System.out.println("Input inválido! Por favor, insira um número.");
        } catch (OutOfTheOptionsException m){
            System.out.println(m);
        }
        return option;
    }

    public static void local (Scanner input, Report report) throws OutOfTheOptionsException{
        int option = 0;
        String local = null;
        try{
        System.out.println("\nInforme a localização: ");
        local = input.nextLine();
        }catch (InputMismatchException z){
            System.out.println("Input inválido! Por favor, insira apenas números e letras.");
        } 

        report.setLocation(local);
        try{
        System.out.println("\nSelecione o bairro: ");
        System.out.println("1. Caxangá");
        System.out.println("2. Dois Irmãos");
        System.out.println("3. Iputinga");
        System.out.println("4. Cordeiro");
        System.out.println("5. Engenho do Meio");
        System.out.println("6. Várzea \n");
        option = input.nextInt();
        input.nextLine();
        if(option < 1 || option > 6){
            throw new OutOfTheOptionsException();
        }
        switch(option){
            case 1:
                report.setDistrict(Districts.Caxangá);
                break;
            case 2:
                report.setDistrict(Districts.DoisIrmãos);
                break;
            case 3:
                report.setDistrict(Districts.Iputinga);
                break;
            case 4:
                report.setDistrict(Districts.Cordeiro);
                break;
            case 5:
                report.setDistrict(Districts.EngenhoDoMeio);
                break;
            case 6:
                report.setDistrict(Districts.Várzea);
                break;
            default:
                throw new OutOfTheOptionsException();
        }
        }catch (InputMismatchException z){
            System.out.println("Input inválido! Por favor, insira um número.");
        } catch (OutOfTheOptionsException m){
            System.out.println(m);
        }
    }

    public static void adicionarMidia(Scanner input, Report report) throws OutOfTheOptionsException{
        int option = 0;
        try{
        System.out.println("\n Deseja adicionar mídia ?");
        System.out.println("1. Sim");
        System.out.println("2. Não \n");
        option = input.nextInt();
        input.nextLine();
        if(option <1 || option >2){
            throw new OutOfTheOptionsException();
        }
        }catch(InputMismatchException t){
            System.out.println("Input inválido! Por favor, insira um número.");
        }catch (OutOfTheOptionsException n){
            System.out.println(n);
        }
        if(option == 1){
            report.setMedia(true);
        }
    }

    public static void problemaAnimal(Scanner input, Issue issue) throws OutOfTheOptionsException{
        int option = 0;
        try{
        System.out.println("\nQue tipo de problema animal deseja relatar ?");
        System.out.println("1. Animal Morto");
        System.out.println("2. Animal Abandonado");
        System.out.println("3. Abuso de Animal");
        System.out.println("4. Peste\n");
        option = input.nextInt();
        input.nextLine();
        if(option < 1 || option > 4){
            throw new OutOfTheOptionsException();
        }

        switch(option){
            case 1:
                issue.setSubtype(Subtypes.AnimalMorto);
                break;
            case 2:
                issue.setSubtype(Subtypes.AnimalAbandonado);
                break;
            case 3:
                issue.setSubtype(Subtypes.AbusoDeAnimal);
                break;
            case 4:
                issue.setSubtype(Subtypes.ControleDePeste);
                break;
            default:
                throw new OutOfTheOptionsException();  
        }
        }catch (InputMismatchException z){
            System.out.println("Input inválido! Por favor, insira um número.");
        } catch (OutOfTheOptionsException m){
            System.out.println(m);
        }
    }

    public static void problemaInfraestrutura(Issue issue, Scanner input) throws OutOfTheOptionsException{
        int option = 0;
        try{
        System.out.println("\nQue tipo de problema de infraestrutura deseja relatar ?");
        System.out.println("1. Vazemento de água.");
        System.out.println("2. Escoamento entupido.");
        System.out.println("3. Saneamento.");
        System.out.println("4. Semáforo.");
        System.out.println("5. Falta de sinalização.");
        System.out.println("6. Iluminação Pública.");
        System.out.println("7. Falta de Energia.");
        System.out.println("8. Rachadura.");
        System.out.println("9. Buraco.");
        System.out.println("10. Falta de Bueiro.\n");

        option = input.nextInt();

        if(option < 1 || option > 10){
            throw new OutOfTheOptionsException();
        }
        input.nextLine();
        switch(option){
            case 1:
                issue.setSubtype(Subtypes.VazamentoDeÁgua);
                break;
            case 2:
                issue.setSubtype(Subtypes.EscoamentoEntupido);
                break;
            case 3:
                issue.setSubtype(Subtypes.Saneamento);
                break;
            case 4:
                issue.setSubtype(Subtypes.Semáforo);
                break;
            case 5:
                issue.setSubtype(Subtypes.FaltaDeSinalização);
                break;
            case 6:
                issue.setSubtype(Subtypes.IluminaçãoPública);
                break;
            case 7:
                issue.setSubtype(Subtypes.FaltaDeEnergia);
                break;
            case 8:
                issue.setSubtype(Subtypes.Rachadura);
                break;
            case 9:
                issue.setSubtype(Subtypes.Buraco);
                break;
            case 10:
                issue.setSubtype(Subtypes.FaltaDeBueiro);
                break;
            default:
                throw new OutOfTheOptionsException();
            
        }
        }catch (InputMismatchException z){
            System.out.println("Input inválido! Por favor, insira um número.");
        } catch (OutOfTheOptionsException m){
            System.out.println(m);
        }

    }

    public static void problemaSeguranca(Issue issue, Scanner input) throws OutOfTheOptionsException{
        int option = 0;
        try{
        System.out.println("\nQue tipo de problema de segurança deseja relatar ?");
        System.out.println("1. Acidente.");
        System.out.println("2. Possível perigo.");
        System.out.println("3. Atividade criminal.");
        System.out.println("4. Pessoa desaparecida;\n");
        option = input.nextInt();
        input.nextLine();
        if(option < 1 || option > 4){
            throw new OutOfTheOptionsException();
        }

        switch(option){
            case 1:
                issue.setSubtype(Subtypes.Acidente);
                break;
            case 2:
                issue.setSubtype(Subtypes.PossívelPerigo);
                break;
            case 3:
                issue.setSubtype(Subtypes.AtividadeCriminal);
                break;
            case 4:
                issue.setSubtype(Subtypes.PessoaDesaparecida);
                break;
            default:
                throw new OutOfTheOptionsException();
            
        }
        }catch (InputMismatchException z){
            System.out.println("Input inválido! Por favor, insira um número.");
        } catch (OutOfTheOptionsException m){
            System.out.println(m);
        }

    }

    public static void problemaLixo(Issue issue, Scanner input) throws OutOfTheOptionsException{
        int option = 0;
        try{
        System.out.println(" \nQue tipo de problema de lixo deseja relatar ?");
        System.out.println("1. Despejo ilegal.");
        System.out.println("2. Área poluída.");
        System.out.println("3. Coleta de item grande.\n");
        option = input.nextInt();
        input.nextLine();
        if(option < 1 || option > 3){
            throw new OutOfTheOptionsException();
        }

        switch (option){
            case 1:
                issue.setSubtype(Subtypes.DespejoIlegal);
                break;
            case 2:
                issue.setSubtype(Subtypes.ÁreaPoluída);   
                break;
            case 3:
                issue.setSubtype(Subtypes.ColetaDeItemGrande);
                break;
            default:
                throw new OutOfTheOptionsException();

        }
        }catch (InputMismatchException z){
            System.out.println("Input inválido! Por favor, insira um número.");
        } catch (OutOfTheOptionsException m){
            System.out.println(m);
        }
    }

    public Object getGreeting() {
        return null;
    }
}
