Thibaut Brunel
Julien Roche
CAR TP03
Master 1 Informatique 2016

----------------------
INTRODUCTION
----------------------
L'objectif de ce TP est de mettre en oeuvre des acteurs réparties avec Akka en Java.
Pour les premières questions, nous travaillons sur une topologie en arbre. Chaque noeud de l'arbre propage les données (chaines de caractère) à ses fils.
La dernière question rajoute un arc entre "D" et "F", nous basculons sur une topologie en graphe.
Afin de visualiser la propagation, les traces d'executions sont fournies.

Réalisation :
- Mise en oeuvre du mécanisme de transfert de base depuis le premier noeud
- Diffusion initiée depuis un autre noeud
- Répartition des acteurs dans différents systèmes
- Gestion de la topologie en graphe donnant lieu à un même "enfant" pour deux noeuds

----------------------
ARCHITECTURE
----------------------
Main : Executable. Affichage des traces d'executions.
ActorRef : Classe Akka permettant de gérer les enfants des noeuds et la propagation.
ActorSystem : Classe Akka modélisant un système, un ensemble d'acteurs ActorRef.
ChildActor : Possède ActorRef + un boolean testant s'il a déjà été visité ou non. Si c'est le cas, il est inutile de lui propager à nouveau le message puisqu'il l'a déjà reçu antérieurement.
Node : Modélisation d'un noeud avec ses enfants. Il étend la class UntypedActor et utilise la méthode surchargée onReceive(Object) lorsqu'il reçoit une information (comme un Message, String ou AddChildActor).
AddChildActor : Permet l'ajout d'un fils à un noeud depuis ActorRef via la méthode tell().
Message : Classe symbolisant un message qui se propage au sein de l'arbre ou du graphe. Il s'agit simplement d'une chaine de caractères.

Nous gérons les exceptions au niveau de la propagation depuis la méthode Node.onReceive(Object). Il permet de soulever une erreur si un dysfonctionnement apparait dans le système de propagation entre acteurs Akka.

Le système de propagation se passe dans la méthode Node.onReceive(Object). L'object peut être de type String, Message ou AddChildActor. AddChildActor permet de réaliser l'initialisation et la construction de l'arbre ou du graphe. Pour la propagation du Message, nous parcourons les enfants ChildActor du noeud Node, vérifions s'ils n'ont pas déjà été visités (et donc ont reçu le message) et, si c'est le cas, nous propageons le message à l'enfant ActorRef:ChildActor.getActor() via la méthode ActorRef.tell(). Un synchronized() est utilisé pour s'assurer que tout se passe correctement et qu'une même action sur un même objet n'est pas en train de se réaliser au même moment. Cela permet tout dysfonctionnement et assure une sécurité.

Les tests couvrent une couverture de 87,9%. Ils ont été réalisés avec JUnit. Le calcul de la couverture s'est fait avec Eclemma pour Eclipse.

----------------------
UTILISATION
----------------------
L'executable est Main. Aucun paramètre n'est nécessaire pour l'execution du programme.
