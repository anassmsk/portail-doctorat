#!/bin/bash
# Script à exécuter une seule fois pour initialiser le dépôt Git

echo "Initialisation du dépôt Git..."
git init
git add .
git commit -m "feat: initialisation de la structure du projet"

echo ""
echo "Ensuite, créez le repo sur GitHub puis exécutez :"
echo "  git remote add origin https://github.com/VOTRE_USERNAME/portail-doctorat.git"
echo "  git branch -M main"
echo "  git push -u origin main"
