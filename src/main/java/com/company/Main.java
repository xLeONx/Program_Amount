package com.company;
import java.util.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

	Scanner scanner = new Scanner(System.in);
	String firstString = "", command = "", banka = "";
	double newValue = 0;
	Map<String, String > account = new HashMap<String, String >();
	do{//проверяем введена ли хоть какая-нибудь команда или пустая строка
		System.out.println("Enter the command");
		firstString = scanner.nextLine();
	}while (firstString.equals(""));
	while( true ) {
		int i = 0;
		boolean flag = false;
		command = "";
		while (i < firstString.length()) {
			if (firstString.toCharArray()[i] == ' ') {
				switch (command) {
					case "NEWACCOUNT": {
						String acc_num = "";
						i++;//увеличиваем счетчик для того чтобы избавиться от считывания первого пробела
						//проверяем введен ли номер счета помимо верной команды
						if ( firstString.length() != 10 ){
							acc_num = firstString.substring(i, firstString.length());
						}else {
							flag = true;
							System.out.println("Invalid command");
							break;
						}

						//проверяем числовой ли номер счета
						Double a = 0.0;//новое число в числовом формате
						try{//проверка введенного параметра для вычета со счета
							a = Double.parseDouble(acc_num);
						}catch (Exception e)
						{
							System.out.println("Input amount error");
							flag = true;
							break;
						}
						if ( acc_num.length() != 5) {
							System.out.println("Input amount error");
						}
						//проверяем создан ли счет с таким номером
						if ( !account.containsKey(acc_num) ) {
							account.put(acc_num, "0.0");
						}else{//если создан выводим сообщение об ошибке ввода
							System.out.println("This account already exists");
							flag = true;
							break;
						}

						flag = true;
						break;
					}
					case "DEPOSIT": {
						i++;//увеличиваем счетчик для того чтобы избавиться от считывания первого пробела
						String acc_num = "", acc_sum = "", aa = "";

						//проверяем введен ли номер счета помимо верной команды
						try {
							acc_num = firstString.substring(i, firstString.indexOf(" ", i));
						}catch (StringIndexOutOfBoundsException e) {
							flag = true;
							System.out.println("Invalid command");
							break;
						}

						if ( account.containsKey(acc_num) ) {//проверяем создан ли счет с таким номером
							acc_sum = account.get(acc_num/*.toString()*/);
						}else{
							System.out.println("Account don`t exist");
							flag = true;
							break;
						}

						newValue = Double.parseDouble(acc_sum);//забираем старое значение

						//проверяем введена ли вносимая сумма помимо верной команды
						try {
							aa = firstString.substring(firstString.lastIndexOf(" ") + 1, firstString.length() );//берем новое значение
						}catch (StringIndexOutOfBoundsException e) {
							flag = true;
							System.out.println("Invalid command");
							break;
						}

						Double a = 0.0;//новое число в числовом формате

						try{//проверка введенного параметра для вычета со счета
							a = Double.parseDouble(aa);
						}catch (Exception e)
						{
							System.out.println("Input amount error");
							flag = true;
							break;
						}
						newValue += a;//добавляем к старому новое
						account.put(firstString.substring(i, firstString.indexOf(" ", i)), String.valueOf(newValue));
						flag = true;
						break;
					}
					case "WITHDROW": {
						i++;//увеличиваем счетчик для того чтобы избавиться от считывания первого пробела
						String acc_num = "", acc_sum = "", aa = "", value = "";

						//проверяем введен ли номер счета помимо верной команды
						try {
							acc_num = firstString.substring(i, firstString.indexOf(" ", i));
						}catch (StringIndexOutOfBoundsException e) {
							flag = true;
							System.out.println("Invalid command");
							break;
						}

						if ( account.containsKey(acc_num) ) {//проверяем создан ли счет с таким номером
							acc_sum = account.get(acc_num/*.toString()*/);
						}else{
							System.out.println("Account don`t exist");
							flag = true;
							break;
						}

						newValue = Double.parseDouble(acc_sum);//забираем старое значение

						//проверяем введена ли информация о снимаемой сумме
						try {
							aa = firstString.substring(firstString.lastIndexOf(" ") + 1, firstString.length() );//берем новое значение
						}catch (StringIndexOutOfBoundsException e) {
							flag = true;
							System.out.println("Invalid command");
							break;
						}

						Double a = 0.0;

						try{//проверка введенного параметра для вычета со счета
							a = Double.parseDouble(aa);
						}catch (Exception e)
						{
							System.out.println("Input amount error");
							flag = true;
							break;
						}

						if (newValue - a >= 0){
							newValue -= a;
						}//вычитаем из старого новое
						account.put(firstString.substring(i, firstString.indexOf(" ", i)), String.valueOf(newValue));
						flag = true;
						break;
					}
					case "BALANCE": {
						String acc_num = "", acc_sum = "";

						//проверяем введена ли информация помимо верной команды
						try {
							acc_num = firstString.substring(firstString.lastIndexOf(" ") + 1, firstString.length());
						}catch (StringIndexOutOfBoundsException e) {
							flag = true;
							System.out.println("Invalid command");
							break;
						}

						if ( account.containsKey(acc_num) ) {//проверяем создан ли счет с таким номером
							acc_sum = account.get(acc_num/*.toString()*/);
						}else{
							System.out.println("Account don`t exist");
							flag = true;
							break;
						}

						System.out.println(acc_sum);
						flag = true;
						break;
					}
					default:{
						flag = true;
						System.out.println("Invalid command");
						break;
					}
				}
				if (flag){//если команда обработана и все ок, выходим из цикла считывания введенной команды и спрашиваем следущую команду
					break;
				}else {//добавлен, чтобы условный оператор не путал что ему использовать и когда
					continue;
				}
			} else {
				command += firstString.toCharArray()[i];
				i++;
				if (i == firstString.length() ){
					System.out.println("Invalid command");
				}
			}
		}
		do{//проверяем введена ли хоть какая-нибудь команда или пустая строка
			System.out.println("Enter the command");
			firstString = scanner.nextLine();
		}while (firstString.equals(""));
		if ( firstString.equals("END") )
		{
			break;
		}
	}

	scanner.close();
	}
}
