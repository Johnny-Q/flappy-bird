#include<iostream>
#include<string.h>
using namespace std;
int main(int argc, char *argv[]){
    if(argc < 2){
        cout << "call this program with the death message you want typed out" << endl;
    }else{
        for(int i = 0; i < strlen(argv[1]); i++){
            for(int j = 0; j <= i; j++){
                cout << argv[1][j];
            }
            cout << endl;
        }
    }
    
}