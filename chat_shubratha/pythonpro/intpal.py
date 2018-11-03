def reverse(num):
    rev = 0    
    while(num > 0):    
        rem = num%10    
        rev = (rev *10) + rem    
        num = num//10
    return rev

def check_palindrome(num):
    Reverse = reverse(num)
    print(Reverse)
    print(num)
    print(num == Reverse)
    if(num == Reverse):
        print("Palindrome")
    else:
        print("Not palindrome")

if __name__ == "__main__":
    n = int(input())
    if (n.isdigit):
        check_palindrome(n)
    else:
        print("No integer detected")