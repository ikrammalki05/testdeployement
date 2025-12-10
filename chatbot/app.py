# chatbot/app.py
# ce code sera améliorer par la suite
import os
import sys
import warnings
import google.generativeai as genai  # type: ignore

warnings.filterwarnings("ignore")
sys.stderr = open(os.devnull, "w")

API_KEY = "AIzaSyAQUnUR_98M95PzU8Ui6I8TEcRA-5Hygeo"  # api key pour cet instant t
genai.configure(api_key=API_KEY)

model = genai.GenerativeModel(model_name="gemini-2.5-flash")

print("Chatbot is ready! Type your messages below (empty line to quit).")

while True:
    question = input("You: ")
    if not question.strip():
        break

    response = model.generate_content(question)
    print("Bot:", response.text, "\n")
