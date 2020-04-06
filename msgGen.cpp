#include<iostream>
#include<string.h>
using namespace std;
int main(int argc, char *argv[]){
    if(argc < 2){
        cout << "call this program with the death message you want typed out" << endl;
    }else{
        for(int z = 1; z < argc; z++){
            for(int i = 0; i < strlen(argv[z]); i++){
                for(int k = 1; k < z; k++){
                    cout << argv[k];
                    cout << " ";
                }
                for(int j = 0; j <= i; j++){
                    cout << argv[z][j];
                }
                cout << endl;
            }
        }
    }
    
}