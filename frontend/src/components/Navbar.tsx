import { useState } from "react";
import { Link } from "react-router-dom";
import { HomeIcon, UserPlusIcon, ArrowLeftOnRectangleIcon, Bars3Icon, XMarkIcon, DocumentTextIcon } from "@heroicons/react/24/solid";

const Navbar = () => {
  const [isOpen, setIsOpen] = useState(false);

  const toggleMenu = () => {
    setIsOpen(!isOpen);
  };

  const navLinks = [
    {to: "/", text: "Strona główna", icon: <HomeIcon className="h-6 w-6" />},
    {to: "/articles", text: "Artykuły", icon: <DocumentTextIcon className="h-6 w-6" />},
    {to: "/login", text: "Logowanie", icon: <ArrowLeftOnRectangleIcon className="h-6 w-6" />},
    {to: "/register", text: "Rejestracja", icon: <UserPlusIcon className="h-6 w-6" />}
  ];

  return (
    <>
      <nav className="hidden md:flex bg-red-800 text-stone-50 px-8 py-4 justify-between items-center">
        <div className="text-xl font-bold text-stone-50">F1 Po Polsku</div>
        <ul className="flex space-x-4">
          {navLinks.map((link) => (
            <li key={link.to}>
              <Link to={link.to} className="hover:bg-red-700 px-3 py-2 rounded-md">
                <span>{link.text}</span>
              </Link>
            </li>
          ))}
        </ul>
      </nav>

      <div className="md:hidden fixed bottom-4 right-4 z-50">
        <button
          onClick={toggleMenu}
          className="cursor-pointer bg-red-800 text-stone-50 p-3 rounded-full shadow-lg focus:outline-none focus:ring-2 focus:ring-slate-500"
          aria-expanded={isOpen}
          aria-controls="mobile-menu"
        >
          {isOpen ? <XMarkIcon className="h-8 w-8" /> : <Bars3Icon className="h-8 w-8" />}
        </button>
        {isOpen && (
          <div
            id="mobile-menu"
            className="absolute bottom-16 right-0 mb-2 w-48 bg-red-800 text-stone-50 rounded-lg shadow-xl overflow-hidden"
          >
            <ul className="flex flex-col p-2">
              {navLinks.map((link) => (
                <li key={link.to} className="w-full">
                  <Link
                    to={link.to}
                    onClick={() => setIsOpen(false)}
                    className="flex items-center space-x-3 px-3 py-3 hover:bg-red-700 rounded-md w-full text-left"
                  >
                    {link.icon}
                    <span>{link.text}</span>
                  </Link>
                </li>
              ))}
            </ul>
          </div>
        )}
      </div>
    </>
  );
};

export default Navbar;