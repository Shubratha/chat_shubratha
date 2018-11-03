def rev(str):
    return str[len(str)::-1]

def check_palindrome(str):
    reverse = rev(str)
    print(reverse)
    print(str)
    print(str == reverse)
    if str == reverse:
        print("Palindrome")
    else:
        print("Not palindrome")

if __name__ == "__main__":
    string = input()
    if(string==""):
        print("No string detected")
        # pass
    else:
        check_palindrome(string)