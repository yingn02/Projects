#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <windows.h>

void question();
void start();
void easter_egg();

int main(void)
{
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 14);
	printf(" �ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�\n");
	printf("��[��Ʃ�� ������ ���� ����]                                       ��\n");
	printf("�Ӹ�� ���� ������ ��ſ��� ���� ����� ���͵帳�ϴ�.                 ��\n");
	printf("��                                                                    ��\n");
	printf("��                                                                    ��\n");
	printf("��[��ɾ�] (��ҹ��� �������)                                        ��\n");
	printf("��S : START - ���⸦ �����մϴ�.                                    ��\n");
	printf("��Q : QUIT - ���α׷��� �����մϴ�.                                   ��\n");
	printf("��                                                                    ��\n");
	printf("�ӡ��߸��� ��ɾ �Է��ϸ� ������ �߻��Ͽ��� �������ֽʽÿ�.        ��\n");
	printf("�ӡؽð��� �߸� �Է����� ���,                                        ��\n");
	printf("���߸� �Է��� �� ��ŭ -1�� ���Ͽ� �Է��Ͻø� �˴ϴ�.                  ��\n");
	printf(" �ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�\n");
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 15);

	question();

	return 0;
}

void question() {
	char user;
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 10);
	printf("\n��ɾ �Է��ϼ���: ");
	scanf(" %c", &user);
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 15);
	printf("\n");

	if (user == 'S' || user == 's')
		start();
	else if (user == 'Q' || user == 'q')
		exit(0);
	else
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 4);
		printf("�߸��� ��ɾ��Դϴ�.");
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 10);
		question();

}

void start() {
	int i = 0, v = 0, total_s = 0;
	int uh = -1, um = -1, us = -1;
	int h = 0, m = 0, s = 0;
	printf(" �ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�\n");
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 10);
	printf("�������� ������ �Է��ϼ���:"); //������ ����
	scanf(" %d", &v);
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 15);
	if (v == 72)
		easter_egg();
	if (v >= 50) {
		printf(" �ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�\n");
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 10); //����
		printf("�������� �ʹ� ���� ��� �� ����Ʈ�� �̿��غ�����.\n");
		printf("�Ʒ� ����Ʈ�� ������ �� \n�������� ��ũ�� �����Ͽ� ����Ʈ�� �ٿ���������.\n\n");
		printf("https://ytplaylist-len.herokuapp.com/");
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 15);
		printf("\n");
	}
	printf(" \n�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�\n");
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 12); //�ð�
	printf("[�ð����� ���ϱ�] (�� �̻� �Է����� �������� 99 �Է�)\n");
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 15);
	printf("����) 1:23:34 �� 1 �Է� \n");
	for (i = 0; uh != 99; i++) {
		printf("�������� ");
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 4);
		printf("%d", i+1);
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 15);
		printf("��° ������ ��, �ʸ� ������\n");
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 10);
		printf("'�ð�'�� �Է��ϼ���: \n");
		scanf(" %d", &uh);
		if (uh == 99)
			h -= 99;
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 15);
		h += uh;
	}
	printf(" \n�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�\n");
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 6); //��
	printf("[�г��� ���ϱ�] (�� �̻� �Է����� �������� 99 �Է�)\n");
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 15);
	printf("����) 1:23:34 �� 23 �Է� \n");
	for (i = 0; um != 99; i++) {
		printf("�������� ");
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 4);
		printf("%d", i + 1);
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 15);
		printf("��° ������ �ð�, �ʸ� ������\n");
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 10);
		printf("'��'�� �Է��ϼ���: \n");
		scanf(" %d", &um);
		if (um == 99)
			m -= 99;
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 15);
		m += um;
	}
	printf(" \n�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�\n");
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 9); //��
	printf("[�ʳ��� ���ϱ�] (�� �̻� �Է����� �������� 99 �Է�)\n");
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 15);
	printf("����) 1:23:34 �� 34 �Է� \n");
	for (i = 0; us != 99; i++) {
		printf("�������� ");
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 4);
		printf("%d", i + 1);
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 15);
		printf("��° ������ �ð�, �и� ������\n");
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 10);
		printf("'��'�� �Է��ϼ���: \n");
		scanf(" %d", &us);
		if (us == 99)
			s -= 99;
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 15);
		s += us;
	}
	printf(" �ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�\n");
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 10); //��� ���
	printf("[��� ���]\n");
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 15);
	printf("�Է��� �������� ����: %d\n", v);
	printf("�Է��� �������� �� ���̴�\n\n");
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 12);
	printf("%d�ð� : %d��\n", h, h*3600);
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 6);
	printf("%d�� : %d��\n", m, m*60);
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 9);
	printf("%d�� : %d��\n\n", s, s);
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 15);

	total_s = h * 3600 + m * 60 + s;
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 10);
	printf("�հ� : %d��\n\n", total_s);
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 15);
	printf("���� �Է��Ͻ� �� �������� ���̴�\n");
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 10);
	printf("%d�ð�", total_s / 3600); 
	total_s %= 3600; 
	printf("%d��", total_s / 60); 
	total_s %= 60;
	printf("%d��", total_s);
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 15);
	printf("�Դϴ�.\n\n");
	Sleep(1500);
	printf("���⸦ �����߽��ϴ�.\n");
	Sleep(1500);
	printf("���� �۾��� �����Ͻ� �� �ֽ��ϴ�.\n\n");
	Sleep(2000);
	main();
}

void easter_egg() {
	int i = 0, v = 0, total_s = 0;
	int uh = -1, um = -1, us = -1;
	int h = 0, m = 0, s = 0;

	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 1);
	printf(" �ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�\n");
	printf("�������� �ʹ� ���� ��� �� ����Ʈ�� �̿��غ�����.\n"); //����
	printf("�Ʒ� ����Ʈ�� ������ �� \n�������� ��ũ�� �����Ͽ� ����Ʈ�� �ٿ���������.\n\n");
	printf("https://ytplaylist-len.herokuapp.com/");
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 9);
	printf("\n");

	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 1);
	printf(" \n�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�\n");
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 9); //�ð�
	printf("[�ð����� ���ϱ�] (�� �̻� �Է����� �������� 99 �Է�)\n");
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 1);
	printf("����) 1:23:34 �� 1 �Է� \n");
	for (i = 0; uh != 99; i++) {
		printf("�������� ");
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 11);
		printf("%d", i + 1);
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 1);
		printf("��° ������ ��, �ʸ� ������\n");
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 9);
		printf("'�ð�'�� �Է��ϼ���: \n");
		scanf(" %d", &uh);
		if (uh == 99)
			h -= 99;
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 1);
		h += uh;
	}
	printf(" \n�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�\n");
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 9); //��
	printf("[�г��� ���ϱ�] (�� �̻� �Է����� �������� 99 �Է�)\n");
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 1);
	printf("����) 1:23:34 �� 23 �Է� \n");
	for (i = 0; um != 99; i++) {
		printf("�������� ");
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 11);
		printf("%d", i + 1);
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 1);
		printf("��° ������ �ð�, �ʸ� ������\n");
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 9);
		printf("'��'�� �Է��ϼ���: \n");
		scanf(" %d", &um);
		if (um == 99)
			m -= 99;
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 1);
		m += um;
	}
	printf(" \n�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�\n");
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 9); //��
	printf("[�ʳ��� ���ϱ�] (�� �̻� �Է����� �������� 99 �Է�)\n");
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 1);
	printf("����) 1:23:34 �� 34 �Է� \n");
	for (i = 0; us != 99; i++) {
		printf("�������� ");
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 11);
		printf("%d", i + 1);
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 1);
		printf("��° ������ �ð�, �и� ������\n");
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 9);
		printf("'��'�� �Է��ϼ���: \n");
		scanf(" %d", &us);
		if (us == 99)
			s -= 99;
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 1);
		s += us;
	}
	printf(" �ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�\n");
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 9); //��� ���
	printf("[��� ���]\n");
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 9);
	printf("�Է��� �������� ����: %d\n", v);
	printf("�Է��� �������� �� ���̴�\n\n");
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 1);
	printf("%d�ð� : %d��\n", h, h * 3600);
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 9);
	printf("%d�� : %d��\n", m, m * 60);
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 1);
	printf("%d�� : %d��\n\n", s, s);
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 15);

	total_s = h * 3600 + m * 60 + s;
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 11);
	printf("�հ� : %d��\n\n", total_s);
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 1);
	printf("���� �Է��Ͻ� �� �������� ���̴�\n");
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 11);
	printf("%d�ð�", total_s / 3600);
	total_s %= 3600;
	printf("%d��", total_s / 60);
	total_s %= 60;
	printf("%d��", total_s);
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 1);
	printf("�Դϴ�.\n\n");
	Sleep(1500);
	printf("���⸦ �����߽��ϴ�.\n");
	Sleep(1500);
	printf("���� �۾��� �����Ͻ� �� �ֽ��ϴ�.\n\n");
	Sleep(2000);
	main();
}