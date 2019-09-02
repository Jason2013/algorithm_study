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


Node* rightRotate(Node* root)
{
    Node* y = root->left;
    Node* t3 = y->right;
    y->right = root;
    root->left = t3;
    return y;
}

Node* leftRotate(Node* root)
{
    Node* y = root->right;
    Node* t2 = y->left;
    y->left = root;
    root->right = t2;
    return y;
}

Node* buildBalancedTree(Node* root)
{
    if (root == NULL) return NULL;
    root->left = buildBalancedTree(root->left);
    root->right = buildBalancedTree(root->right);

    int lDepth = height(root->left);
    int rDepth = height(root->right);

    if (lDepth - rDepth > 1)
    {
        if (height(root->left->left) >= height(root->left->right))
        {
            // left - left
            root = rightRotate(root);
        }
        else
        {
            // left - right
            root->left = leftRotate(root->left);
            root = rightRotate(root);
        }

    }
    else if (lDepth - rDepth < -1)
    {
        if (height(root->right->left) <= height(root->right->right))
        {
            // right - right
            root = leftRotate(root);
        }
        else
        {
            // right - left
            root->right = rightRotate(root->right);
            root = leftRotate(root);
        }
    }

    return root;
}

