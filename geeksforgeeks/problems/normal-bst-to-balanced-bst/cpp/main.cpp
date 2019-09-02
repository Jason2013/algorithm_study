#include <iostream>
    using namespace std;
/* { */

    struct Node
    {
        int data;
        Node* left, *right;
    };

    Node* newNode(int data)
    {
        Node* node = new Node;
        node->data = data;
        node->left = node->right = NULL;
        return node;
    }

    Node* insert(Node* node, int key)
    {
        if (node == NULL) return newNode(key);
        if (key < node->data)
            node->left = insert(node->left, key);
        else if (key > node->data)
            node->right = insert(node->right, key);
        return node;
    }

    void preOrder(Node* node)
    {
        if (node == NULL) return;
        cout << node->data << ' ';
        preOrder(node->left);
        preOrder(node->right);
    }

    int height(Node* node)
    {
        if (node == NULL) return 0;
        int lDepth = height(node->left);
        int rDepth = height(node->right);
        return ((lDepth > rDepth) ? lDepth : rDepth) + 1;
    }
/* } */

Node* buildBalancedTree(Node* root);
int main()
{
    int t;
    cin >> t;
    while (t--)
    {
        struct Node* root = NULL;
        int n, temp;
        cin >> n;
        while(n--)
        {
            cin >> temp;
            root = insert(root, temp);
        }

        root = buildBalancedTree(root);
        cout << height(root) << endl;
    }
    return 0;
}


Node* buildBalancedTree(Node* root)
{
    return NULL;
}

